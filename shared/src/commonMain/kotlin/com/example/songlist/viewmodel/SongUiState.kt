package com.example.songlist.viewmodel

import com.example.songlist.models.Song

sealed class SongUiState {
    data class Data(val songs: List<Song>): SongUiState()
    object Loading: SongUiState()
}