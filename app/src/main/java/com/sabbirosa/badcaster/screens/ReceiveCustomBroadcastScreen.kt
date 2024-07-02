package com.sabbirosa.badcaster.ui.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class CustomReceiver(private val onMessageReceived: (String) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("broadcast_message") ?: "No message"
        onMessageReceived(message)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CustomBroadcastReceiveScreen() {
    var CustomReceivedMessage by remember {
        mutableStateOf("No Message Found")
    }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val receiver = CustomReceiver { message ->
            CustomReceivedMessage = message
        }

        val filter = IntentFilter("com.sabbirosa.badcaster.CUSTOM_BROADCAST")
        context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Toast.makeText(context, "Custom Broadcast Message: $CustomReceivedMessage", Toast.LENGTH_SHORT).show()

    Text(
        text = "Custom Received Message: $CustomReceivedMessage",
        modifier = Modifier
            .padding(top = 20.dp)
    )

}