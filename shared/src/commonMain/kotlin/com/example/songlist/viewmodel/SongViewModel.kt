package com.example.songlist.viewmodel

import com.doublesymmetry.viewmodel.ViewModel
import com.example.songlist.api.SongService
import com.example.songlist.models.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SongViewModel: ViewModel() {
    private val _viewState = MutableStateFlow<SongUiState>(SongUiState.Loading)
    val viewState: StateFlow<SongUiState> = _viewState

    fun onLaunched() {
        scope.launch {
            try {
                val songs = SongService().getSongList()
                _viewState.value = SongUiState.Data(songs)
            } catch (e: Exception) {
                _viewState.value = SongUiState.Data(emptyList())
            }
        }
    }

    /**
     * Convenience method for iOS observing the [viewState]
     */
    @Suppress("unused")
    fun observeViewState(onChange: (SongUiState) -> Unit) {
        viewState.onEach {
            onChange(it)
        }.launchIn(scope)
    }
}