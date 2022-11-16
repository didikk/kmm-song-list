import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            NavigationView {
                ContentView()
                    .navigationTitle("Song List")
                    .navigationBarTitleDisplayMode(.inline)
            }
        }
    }
}
