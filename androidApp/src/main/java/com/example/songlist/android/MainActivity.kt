package com.example.songlist.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.example.songlist.models.Song
import com.example.songlist.viewmodel.SongUiState
import com.example.songlist.viewmodel.SongViewModel

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            background = Color("#F2F2F7".toColorInt())
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            background = Color("#F2F2F7".toColorInt())
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    private val viewModel: SongViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Song List") },
                            backgroundColor = MaterialTheme.colors.background,
                            elevation = 0.dp
                        )
                    },
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(true) {
                        viewModel.getSongList()
                    }

                    when(val state = viewModel.viewState.collectAsState().value) {
                        is SongUiState.Loading -> Loading()
                        is SongUiState.Data -> SongCollection(songs = state.songs)
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun SongCollection(songs: List<Song>) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        LazyColumn {
            items(songs) {
                SongCard(it)
                Divider(
                    color = Color("#DFDFDF".toColorInt()),
                    modifier = Modifier.padding(start = 78.dp)
                )
            }
        }
    }
}

@Composable
fun SongCard(data: Song) {
    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = data.artworkUrl100,
            contentDescription = "Album image"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(text = data.trackName, style = MaterialTheme.typography.h6)
            Text(
                text = data.artistName,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Gray
            )
        }
    }
}
