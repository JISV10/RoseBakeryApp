package com.example.rosebakeryapp

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

class NewShoppingList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoseBakeryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShoppingList()
                }
            }
        }
    }
}

@Composable
fun ShoppingList( modifier: Modifier = Modifier) {
    val salmonPink = colorResource(id = R.color.salmon_pink)
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(30.dp)
                .size(width = 250.dp, height = 80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = salmonPink)

        ) {

            Text(
                stringResource(id = R.string.new_shopping_list),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(35.dp)
                .size(width = 250.dp, height = 80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = salmonPink)

        ) {

            Text(
                stringResource(id = R.string.create_shopping_list),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewShoppingListPreview() {
    RoseBakeryAppTheme {
        ShoppingList()
    }
}