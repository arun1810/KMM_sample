import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
		Text(greet)
	}
}

extension ContentView{
    class ViewModel:ObservableObject{
        @Published var text = "Loading..."
        init(){
            //data will be loaded here
            Greeting().greet{ greeting, error in
                DispatchQueue.main.async{ //while calling coroutine in iOS, the completion handlers might call on threads other than main, so this line ensures the text update in main thread.
                    if let greeting = greeting{
                        self.text = greeting
                    }
                    else{
                        self.text = error?.localizedDescription ?? "error"
                    }
                }

            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
