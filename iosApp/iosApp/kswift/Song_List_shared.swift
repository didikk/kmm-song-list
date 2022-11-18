// This file automatically generated by MOKO KSwift (https://github.com/icerockdev/moko-kswift)
//
import shared

/**
 * selector: ClassContext/Song_List:shared/com/example/songlist/viewmodel/SongUiState */
public enum SongUiStateKs {

  case data(SongUiState.Data)
  case loading

  public var sealed: SongUiState {
    switch self {
    case .data(let obj):
      return obj as! shared.SongUiState
    case .loading:
      return shared.SongUiState.Loading() as shared.SongUiState
    }
  }

  public init(_ obj: SongUiState) {
    if let obj = obj as? shared.SongUiState.Data {
      self = .data(obj)
    } else if obj is shared.SongUiState.Loading {
      self = .loading
    } else {
      fatalError("SongUiStateKs not synchronized with SongUiState class")
    }
  }

}