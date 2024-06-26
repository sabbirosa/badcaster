package com.sabbirosa.badcaster.screens

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sabbirosa.badcaster.ui.screens.CustomBroadcastReceiveScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CustomBroadcastSendScreen(){
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    var clicked by remember { mutableStateOf(false) }

    TextField(value = text, onValueChange = {text = it})
    Button(
        onClick = {
            sendBroadCast(context, text)
            clicked = true
        },
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        Text(text = "Broadcast")
    }
    CustomBroadcastReceiveScreen()
}

private fun sendBroadCast(context: Context, text: String){
    val intent = Intent("com.sabbirosa.badcaster.CUSTOM_BROADCAST").apply {
        putExtra("broadcast_message", text)
    }
    context.sendBroadcast(intent)
    Toast.makeText(context, "Custom Broadcast Message: $text", Toast.LENGTH_SHORT).show()
}