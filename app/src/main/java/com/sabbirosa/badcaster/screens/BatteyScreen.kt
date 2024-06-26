package com.sabbirosa.badcaster.screens

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@SuppressLint("RememberReturnType")
@Composable
fun BatteryScreen(){
    val context = LocalContext.current
    var intText: Int? = null
    var guessIsCorrect by remember {
        mutableStateOf(false)
    }
    val batteryChangeReciever = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
                guessIsCorrect = (level == intText)
            }
        }
    }
    DisposableEffect(Unit) {

        context.registerReceiver(batteryChangeReciever, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        onDispose {

        }
    }

    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val batteryPercentage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)


    Text(text = "Batter Percentage: $batteryPercentage")
}