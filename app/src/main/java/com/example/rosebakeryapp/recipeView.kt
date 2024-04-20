package com.example.rosebakeryapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeView(navController: NavController, recipeId: String) {
    val recipe = fetchRecipeById(recipeId)
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = recipe.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("recipeNote") }) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                    }
                }
            )
            // Contenido de la pantalla
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = recipe.image,
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = recipe.ingredients,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Instructions",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f),
                ) {
                    item {
                        Text(
                            text = recipe.description,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun fetchRecipeById(recipeId: String): Recipe {
    // Placeholder data, replace with actual fetching logic
    return Recipe(
        id = recipeId,
        title = "Brownie",
        ingredients = "140 grams dark chocolate\n" +
                "80 grams butter\n" +
                "2 large eggs\n" +
                "1 cup white sugar\n" +
                "1/2 cup all-purpose flour\n" +
                "1/2 teaspoon salt\n" +
                "1 tablespoon vanilla extract\n",
        description = "Now, here's a brief instruction for making a chocolate cake:\n" +
                "\n" +
                "Preheat your oven to 350°F (180°C). Grease and flour a round cake pan or line it with parchment paper.\n" +
                "In a heatproof bowl, melt the dark chocolate and butter together. You can do this over a pot of simmering water or in the microwave, stirring occasionally until smooth. Let it cool slightly.\n" +
                "In another bowl, beat the eggs and sugar together until creamy and slightly thickened.\n" +
                "Gradually pour the melted chocolate mixture into the egg mixture while stirring continuously.\n" +
                "Add the vanilla extract and mix well.\n" +
                "Sift in the flour and salt, and gently fold them into the chocolate mixture until just combined. Be careful not to overmix.\n" +
                "Pour the batter into the prepared cake pan and spread it evenly.\n" +
                "Bake in the preheated oven for about 25-30 minutes, or until a toothpick inserted into the center comes out clean.\n" +
                "Once baked, remove the cake from the oven and let it cool in the pan for 10 minutes before transferring it to a wire rack to cool completely.\n" +
                "Once cooled, you can dust the cake with powdered sugar or serve it with whipped cream or ice cream. Enjoy your homemade chocolate cake!",
        image = painterResource(id = R.drawable.brownie)
    )
}


@Preview
@Composable
fun RecipeViewPreview() {
    val mockNavController = rememberNavController()
    RecipeView(navController = mockNavController, recipeId = "1")
}
