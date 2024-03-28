package com.example.rosebakeryapp

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

class NewShoppingListClass : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoseBakeryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewShoppingList()
                }
            }
        }
    }
}

@Composable
fun NewShoppingList( modifier: Modifier = Modifier) {
    val salmonPink = colorResource(id = R.color.salmon_pink)
    var text by remember { mutableStateOf("")}
    var quantity by remember { mutableStateOf("") }
    val shoppingLista = remember { mutableStateListOf<Pair<String, String>>() }


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(text = "Item")},
            modifier = Modifier.padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextField(
            value = quantity,
            onValueChange = {quantity = it},
            label = { Text(text = "Quantity")},
            modifier = Modifier.padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                      if(text.isNotEmpty() && quantity.isNotEmpty()){
                          shoppingLista.add(Pair(text, quantity))
                          text = ""
                          quantity = ""
                      }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
            ,
            colors = ButtonDefaults.buttonColors(containerColor = salmonPink)
            ) {
            Text(text = "Add Item")
        }
    }
    LazyColumn{
        items(shoppingLista) { item ->
            Row(
                modifier.padding(vertical = 6.dp)
            ){
                Text("${item.first} - ${item.second}",
                    style = MaterialTheme.typography.bodyMedium )
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun NewShoppingListPreview() {
    RoseBakeryAppTheme {
        NewShoppingList()
    }
}