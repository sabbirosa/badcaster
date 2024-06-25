package com.sabbirosa.badcaster.screens

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabbirosa.badcaster.R

@Composable
fun AudioScreen(
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    val audioUri = Uri.parse("android.resource://com.sabbirosa.badcaster/raw/badcaster_audio")

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Audio Screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Black,
            style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        )
        AudioPlayer(audioUri = audioUri, context = context)
    }
}

@Composable
fun AudioPlayer(
    audioUri: Uri,
    context: Context
) {
    var isPlaying by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer.create(context, audioUri) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = if (isMuted) R.drawable.music_muted else R.drawable.music_note),
            contentDescription = "Music Note",
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    isMuted = !isMuted
                    if (isMuted) {
                        mediaPlayer.setVolume(0f, 0f)
                    } else {
                        mediaPlayer.setVolume(1f, 1f)
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play Button",
                modifier = Modifier
                    .size(64.dp)
                    .clickable {
                        if (!isPlaying) {
                            mediaPlayer.start()
                            isPlaying = true
                            isMuted = false
                        }
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.pause_button),
                contentDescription = "Pause Button",
                modifier = Modifier
                    .size(64.dp)
                    .clickable {
                        if (isPlaying) {
                            mediaPlayer.pause()
                            isPlaying = false
                            isMuted = true
                        }
                    }
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}
