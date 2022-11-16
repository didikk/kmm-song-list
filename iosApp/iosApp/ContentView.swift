import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greeting()
    
    let songs = [1, 2, 3, 4, 5]
    
    var body: some View {
        List(songs, id: \.self) { song in
            HStack {
                ImageView(withURL: "https://is4-ssl.mzstatic.com/image/thumb/Music112/v4/32/cb/9d/32cb9d04-ef0f-93bb-fd2f-19b395785025/075679956187.jpg/600x600bb.jpg")
                    .clipShape(Circle())
                
                VStack(alignment: .leading) {
                    Text("Still Into You")
                        .font(.system(size: 20, weight: .medium))
                    Text("Paramore")
                        .font(.system(size: 12))
                        .foregroundColor(.gray)
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
