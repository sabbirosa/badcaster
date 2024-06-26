package com.sabbirosa.badcaster

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sabbirosa.badcaster.navigation.NavbarLayout
import com.sabbirosa.badcaster.navigation.NavigationItem
import com.sabbirosa.badcaster.navigation.Screens
import com.sabbirosa.badcaster.navigation.setupNavGraph
import com.sabbirosa.badcaster.ui.theme.BadCasterTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BadCasterTheme {
                val items = listOf(
                    NavigationItem(title = "Broadcast", route = Screens.Broadcast.route),
                    NavigationItem(title = "Image", route = Screens.Image.route),
                    NavigationItem(title = "Audio", route = Screens.Audio.route),
                    NavigationItem(title = "Video", route = Screens.Video.route),
                )
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                ModalNavigationDrawer(drawerContent = {

                   ModalDrawerSheet {
                        NavbarLayout(items = items, currentRoute = Screens.Broadcast.route) {currentNavigationItem ->
                            navController.navigate(currentNavigationItem.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                   }
                }, drawerState = drawerState) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("BadCaster") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(imageVector = Icons.Default.Menu, contentDescription = "BadCaster App Menu Icon")
                                    }
                                }
                            )
                        }
                    ) {innerPadding ->
                        setupNavGraph(navController = navController, innerPadding = innerPadding)
                    }
                }
            }
        }

    }
}