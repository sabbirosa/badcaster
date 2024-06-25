package com.sabbirosa.badcaster.screens

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoScreen(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)
    ) {
        val videoUri = Uri.parse("android.resource://com.sabbirosa.badcaster/raw/badcaster_video")
        Text(text = "Video Screen", fontSize = 24.sp, modifier = Modifier.padding(16.dp), color = Color.Black, style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
        VideoPlayer(videoUri)
    }
}

@Composable
fun VideoPlayer(
    videoUri: Uri
) {
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoURI(videoUri)
                val videoController = MediaController(context)
                videoController.setAnchorView(this)
                setMediaController(videoController)

                setOnPreparedListener {
                    start()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
            .heightIn(min = 200.dp)
    )
}
