import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            NavigationView {
                ContentView(viewModel: ContentView.ViewModel())
                    .navigationTitle("Song List")
                    .navigationBarTitleDisplayMode(.inline)
            }
        }
    }
}
