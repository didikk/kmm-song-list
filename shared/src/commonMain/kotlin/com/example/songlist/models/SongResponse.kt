package com.example.songlist.models

import kotlinx.serialization.Serializable

@Serializable
data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)