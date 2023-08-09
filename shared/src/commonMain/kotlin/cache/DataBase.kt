package cache

import com.example.kmm_test_2.entity.Links
import com.example.kmm_test_2.entity.Patch
import com.example.kmm_test_2.entity.RocketLaunch
import com.jetbrains.handson.kmm.shared.cache.AppDatabase

internal class DataBase(databaseDriverFactory: DatabaseDriverFactory) { //internal means only visible inside the same module( similar to protected)(in this case it iis only accessible through the multiplatform module)

     private val database = AppDatabase(databaseDriverFactory.createDriver())
     private val dbQuery = database.appDatabaseQueries

     internal fun clearDatabase(){
          dbQuery.transaction {
               dbQuery.removeAllLaunches()
          }
     }

     internal fun getAllLaunches():List<RocketLaunch>{
          return dbQuery.selelctAllLaunchInfo(::mapLaunchSelecting).executeAsList()
     }

     private fun mapLaunchSelecting( //this maps the data entity class to RocketLaunch data model
          flightNumber:Long,
          missionName:String,
          details:String?,
          launchSuccess:Boolean?,
          launchDateUTC:String,
          patchUrlSmall:String?,
          patchUrlLarge:String?,
          articleUrl:String?
     ):RocketLaunch{
          return RocketLaunch(
               flightNumber = flightNumber.toInt(),
               missionName = missionName,
               details  = details,
               launchDateUTC = launchDateUTC,
               launchSuccess = launchSuccess,
               links = Links(
                    patch = Patch(
                         small = patchUrlSmall,
                         large = patchUrlLarge
                    ),
                    article = articleUrl
               )
          )
     }

     private fun insertLaunch(launch :RocketLaunch){
          dbQuery.insertLaunch(
               flightNumber = launch.flightNumber.toLong(),
               missionName = launch.missionName,
               details = launch.details,
               launchSuccess = launch.launchSuccess,
               launchDateUTC = launch.launchDateUTC,
               patchUrlSmall = launch.links.patch.small,
               patchUrlLarge = launch.links.patch.large,
               articleUrl = launch.links.article
          )
     }

     fun createLaunches(rocketLaunched:List<RocketLaunch>){
          rocketLaunched.forEach {
               insertLaunch(it)
          }
     }
}