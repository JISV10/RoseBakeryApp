package com.example.rosebakeryapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    //val salmonPink = colorResource(id = R.color.salmon_pink)
    var text by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    val shoppingLista = remember { mutableStateListOf<Triple<String, String, Boolean>>() }

    Scaffold(
        topBar = {
            RoseTopAppBar()
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "Item") },
                modifier = Modifier.padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text(text = "Quantity") },
                modifier = Modifier.padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    if (text.isNotEmpty() && quantity.isNotEmpty()) {
                        shoppingLista.add(Triple(text, quantity, false))
                        text = ""
                        quantity = ""
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                //colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Add Item")
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(shoppingLista) { item ->
                    Row(
                        modifier.padding(vertical = 6.dp)
                    ) {
                        ShoppingItemCard(item = item, onCheckedChange = { isChecked ->
                            val index = shoppingLista.indexOf(item)
                            if (index != -1) {
                                shoppingLista[index] = Triple(item.first, item.second, isChecked)
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingItemCard(item: Triple<String, String, Boolean>, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = item.third,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${item.first} - ${item.second}")
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