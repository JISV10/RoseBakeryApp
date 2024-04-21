package com.example.rosebakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
    var inputValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var selectedConversion by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        RoseTopAppBar()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            ConversionContent(
                inputValue = inputValue,
                onInputValueChanged = { inputValue = it },
                selectedConversion = selectedConversion,
                onConversionSelected = { selectedConversion = it },
                onConvertClicked = {
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
                result = result
            )
        }
    }
}

@Composable
fun ConversionContent(
    inputValue: String,
    onInputValueChanged: (String) -> Unit,
    selectedConversion: String,
    onConversionSelected: (String) -> Unit,
    onConvertClicked: () -> Unit,
    result: String
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputValue,
            onValueChange = onInputValueChanged,
            label = { Text("Enter value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConversionsList(
            conversions = listOf(
                "Kilograms to Pounds",
                "Pounds to Kilograms",
                "Liters to Gallons",
                "Gallons to Liters"
            ),
            selectedConversion = selectedConversion,
            onConversionSelected = onConversionSelected
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onConvertClicked,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Convert",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ConversionsList(
    conversions: List<String>,
    selectedConversion: String,
    onConversionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Conversions",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
        conversions.forEach { conversion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedConversion == conversion,
                    onClick = { onConversionSelected(conversion) }
                )
                Text(text = conversion)
            }
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