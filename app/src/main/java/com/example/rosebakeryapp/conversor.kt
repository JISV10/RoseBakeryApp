package com.example.rosebakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

class ConversorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConversionScreen()
        }
    }
}

@Composable
fun ConversionScreen() {
    val salmonPink = colorResource(id = R.color.salmon_pink)
    var inputValue by remember { mutableStateOf("") }
    var selectedConversion by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val conversions = listOf(
        "Kilograms to Pounds",
        "Pounds to Kilograms",
        "Liters to Gallons",
        "Gallons to Liters"
    )


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                conversions.forEach { conversion ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedConversion == conversion,
                            onClick = { selectedConversion = conversion }
                        )
                        Text(text = conversion)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (inputValue.isNotEmpty()) {
                            result = when (selectedConversion) {
                                "Kilograms to Pounds" -> "${inputValue.toDouble() * 2.20462} lbs"
                                "Pounds to Kilograms" -> "${inputValue.toDouble() / 2.20462} kg"
                                "Liters to Gallons" -> "${inputValue.toDouble() / 3.78541} gal"
                                "Gallons to Liters" -> "${inputValue.toDouble() * 3.78541} L"
                                else -> ""
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = salmonPink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Convert",
                        fontFamily = FontFamily.Serif
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = result)
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun ConversorPreview() {
    RoseBakeryAppTheme {
        ConversionScreen()
    }
}