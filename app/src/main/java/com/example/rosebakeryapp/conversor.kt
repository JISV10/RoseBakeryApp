package com.example.rosebakeryapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme


class ConversionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoseBakeryAppTheme {
                // A surface container using the 'background' color from the theme
                ConversionScreen()
            }
        }
    }
}

@Composable
fun ConversionScreen() {
    var weightInput by remember { mutableStateOf(TextFieldValue("3.00")) }
    var volumeInput by remember { mutableStateOf(TextFieldValue("0.00")) }
    val weightOutput by remember { mutableStateOf("6.61387") }
    var volumeOutput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConversionField(
            label = "KG",
            textFieldValue = weightInput,
            onValueChange = { weightInput = it }
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                volumeOutput = convertLiterToGallon(volumeInput.text.toDoubleOrNull() ?: 0.0)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA07A))
        ) {
            Text("Convert")
        }
        Spacer(Modifier.height(8.dp))
        ConversionField(
            label = "LB",
            textFieldValue = TextFieldValue(weightOutput),
            onValueChange = {},
            readOnly = true
        )
        Spacer(Modifier.height(24.dp))
        ConversionField(
            label = "Liter",
            textFieldValue = volumeInput,
            onValueChange = { volumeInput = it }
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                volumeOutput = convertLiterToGallon(volumeInput.text.toDoubleOrNull() ?: 0.0)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA07A))
        ) {
            Text("Convert")
        }
        Spacer(Modifier.height(8.dp))
        ConversionField(
            label = "Gallon",
            textFieldValue = TextFieldValue(volumeOutput),
            onValueChange = {},
            readOnly = true
        )
    }
}

@Composable
fun ConversionField(
    label: String,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    readOnly: Boolean = false
) {
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        label = { Text(label) },
        readOnly = readOnly
    )
}

fun convertLiterToGallon(liter: Double): String {
    val gallon = liter * 0.264172
    return "%.5f".format(gallon)
}

@Preview(showBackground = true)
@Composable
fun ConversionScreenPreview() {
    RoseBakeryAppTheme {
        ConversionScreen()
    }
}