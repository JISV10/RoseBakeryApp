package com.example.rosebakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoseBakeryAppTheme {
                val salmonPink = colorResource(id = R.color.salmon_pink)
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = salmonPink
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("newRecipe") { NewRecipeScreen() }
                        composable("shoppingList") { NewShoppingList() }
                        composable("timer") { TimerScreen() }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController? = null) {
    val salmonPink = colorResource(id = R.color.salmon_pink)
    Scaffold(
        topBar = {
            RoseTopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController?.navigate("newRecipe") },
                colors = ButtonDefaults.buttonColors(containerColor = salmonPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text("My Recipe",
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController?.navigate("shoppingList") },
                colors = ButtonDefaults.buttonColors(containerColor = salmonPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text("Shopping List",
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController?.navigate("timer") },
                colors = ButtonDefaults.buttonColors(containerColor = salmonPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text("Timer",
                    fontFamily = FontFamily.Serif
                    )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoseTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(


        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(id = R.drawable.roselogoround),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(id = R.string.top_bar),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RoseBakeryAppTheme {
        MainScreen()
    }
}