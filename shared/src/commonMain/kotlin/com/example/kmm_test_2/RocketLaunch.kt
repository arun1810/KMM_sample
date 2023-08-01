import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // for kotlinx.serialization plugin to automatically generate a default serializer for it
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber:Int,
    @SerialName("name")
    val missionName:String,
    @SerialName("date_utc")
    val launchDateUTC:String,
    @SerialName("success")
    val launchSuccess:Boolean?,
    )