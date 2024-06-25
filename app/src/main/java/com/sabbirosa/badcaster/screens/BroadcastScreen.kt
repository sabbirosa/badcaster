package com.sabbirosa.badcaster.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BroadcastScreen(
    innerPadding: PaddingValues
) {

    val options = listOf("Custom broadcast receiver", "System battery notification receiver")

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Broadcast Screen", fontSize = 24.sp, modifier = Modifier.padding(16.dp), color = Color.Black, style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
        DropdownComponent(options = options)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownComponent(
    options: List<String>
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
            ) {
            TextField(modifier = Modifier.menuAnchor(), value = selectedOption, onValueChange = {}, readOnly = true)
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                
                options.forEachIndexed { index, option ->  
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        selectedOption = options[index]
                        isExpanded = false
                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                        )
                }
            }
            }

        Button(onClick = {
            // Show selected option
        }) {
            Text("Select Option")
        }
        Text(text = "Currently selected: $selectedOption", fontSize = 16.sp, modifier = Modifier.padding(16.dp), color = Color.Black)
    }
}