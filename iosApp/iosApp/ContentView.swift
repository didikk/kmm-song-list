import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    let greet = Greeting().greeting()
    
    var body: some View {
        switch viewModel.viewState {
        case is SongUiState.Loading: ProgressView()
        case let data as SongUiState.Data: SongCollection(songs: data.songs)
        default: EmptyView()
        }
    }
}

struct SongCollection: View {
    private(set) var songs: [Song]
    
    var body: some View {
        List(songs, id: \.self) { song in
            HStack {
                ImageView(withURL: song.artworkUrl100)
                    .clipShape(Circle())
                
                VStack(alignment: .leading) {
                    Text(song.trackName)
                        .font(.system(size: 20, weight: .medium))
                    Text(song.artistName)
                        .font(.system(size: 12))
                        .foregroundColor(.gray)
                }
            }
        }
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var viewState: SongUiState = SongUiState.Loading()
        
        let songViewModel = SongViewModel()
        
        init() {
            songViewModel.onLaunched()
            
            songViewModel.observeViewState{ state in
                self.viewState = state
            }
        }
        
        deinit {
            songViewModel.clear()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}
