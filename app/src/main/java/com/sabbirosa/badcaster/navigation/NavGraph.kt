package com.sabbirosa.badcaster.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sabbirosa.badcaster.screens.AudioScreen
import com.sabbirosa.badcaster.screens.BroadcastScreen
import com.sabbirosa.badcaster.screens.ImageScreen
import com.sabbirosa.badcaster.screens.VideoScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun setupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
        NavHost(navController = navController, startDestination = Screens.Broadcast.route) {
            composable(Screens.Broadcast.route) {
                BroadcastScreen(innerPadding = innerPadding)
            }

            composable(Screens.Image.route) {
                ImageScreen(innerPadding = innerPadding)
            }

            composable(Screens.Audio.route) {
                AudioScreen(innerPadding = innerPadding)
            }

            composable(Screens.Video.route) {
                VideoScreen(innerPadding = innerPadding)
            }
    }
}