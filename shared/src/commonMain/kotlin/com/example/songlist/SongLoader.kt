package com.example.songlist

import com.example.songlist.models.Song
import com.example.songlist.models.SongResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SongLoader {
    private val httpClient = HttpClient()

//    private val httpClient = HttpClient {
//        install(ContentNegotiation) {
//            json(Json {
//                prettyPrint = true
//                isLenient = true
//                ignoreUnknownKeys = true
//            })
//        }
//    }

    @Throws(Exception::class)
    suspend fun getSongList(): List<Song> {
        val url = "https://itunes.apple.com/search?entity=song&limit=10&offset=0&term=paramore"
        val resString: String = httpClient.get(url).body()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val songResponse: SongResponse = json.decodeFromString(resString)

        return songResponse.results
    }
}