package com.sabbirosa.badcaster.navigation

sealed class Screens(var route: String) {
    object Broadcast : Screens("broadcast")
    object Image : Screens("image")
    object Audio : Screens("audio")
    object Video : Screens("video")
}