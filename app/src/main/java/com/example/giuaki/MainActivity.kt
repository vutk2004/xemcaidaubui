package com.example.giuaki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureApp()
        }
    }
}

@Composable
fun TemperatureApp() {
    var temperatureInput by remember { mutableStateOf("") }
    val temperature = temperatureInput.toFloatOrNull() ?: 0f
    val (color, label) = getTemperatureColorAndLabel(temperature)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.climate_control_app_icon_temperature_regulation_thermometer_with_down_and_up_arrows_ui_ux_user_interface_web_or_mobile_application_isolated_illustration_vector), contentDescription = "Temperature",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Temperature App",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        TextField(
            value = temperatureInput,
            onValueChange = { temperatureInput = it },
            label = { Text("Enter temperature (°C)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(150.dp)
                .background(color, shape = CircleShape)
        ) {
            Text(text = label, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

fun getTemperatureColorAndLabel(temperature: Float): Pair<Color, String> {
    return when {
        temperature < 25 -> Pair(Color.Blue, "Cold")
        temperature in 25f..28f -> Pair(Color.Green, "Mild")
        temperature in 29f..35f -> Pair(Color(0xFFFFA500), "Hot")
        else -> Pair(Color.Red, "Very Hot")
    }
}
