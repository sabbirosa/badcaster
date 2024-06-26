package com.sabbirosa.badcaster.screens

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabbirosa.badcaster.ui.screens.CustomBroadcastReceiveScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun BroadcastScreen(
    innerPadding: PaddingValues
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    val options = listOf("Custom broadcast receiver", "System battery notification receiver")

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Broadcast Screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Black,
            style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        )

        when (selectedOption) {
            null -> DropdownComponent(options = options, onOptionSelected = { selectedOption = it })
        }

        when (selectedOption) {
            "Custom broadcast receiver" -> Text(
                text = "This screen allows you to send a custom broadcast message to the system. You can also receive the broadcast message.",
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
            "System battery notification receiver" -> Text(
                text = "This screen allows you to receive the battery level notification from the system.",
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }

        selectedOption?.let { option ->
            when (option) {
                "Custom broadcast receiver" -> CustomBroadcastSendScreen()
                "System battery notification receiver" -> BatteryScreen()
                else -> Text("Unknown option selected")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownComponent(
    options: List<String>,
    onOptionSelected: (String) -> Unit
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
            TextField(
                modifier = Modifier.menuAnchor(),
                value = selectedOption,
                onValueChange = {},
                readOnly = true
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption = options[index]
                            onOptionSelected(option)
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}


