package com.example.rosebakeryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Assuming Recipe data class is defined elsewhere and available here
// If not, ensure to define it based on your application's requirements
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val image: Painter,
    val ingredients: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(navController: NavController) {
    val recipes = sampleRecipes() // Function to provide sample recipes, defined below

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipes") },
                actions = {
                    IconButton(onClick = { /* Navigate to add new recipe screen */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Add Recipe")
                    }
                }
            )
        }
    ) { padding ->
        RecipeList(
            recipes = recipes,
            modifier = Modifier.padding(padding),
            onDeleteRecipeClick = { /* Handle delete */ },
            onEditRecipeClick = { /* Handle edit */ },
            onItemClick = { recipeId ->
                navController.navigate("recipeView/$recipeId")
            }
        )
    }
}

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
    onDeleteRecipeClick: (String) -> Unit,
    onEditRecipeClick: (Recipe) -> Unit,
    onItemClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onDeleteClick = onDeleteRecipeClick,
                onEditClick = onEditRecipeClick,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onDeleteClick: (String) -> Unit,
    onEditClick: (Recipe) -> Unit,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(recipe.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically
                , modifier = Modifier.fillMaxWidth().fillMaxSize()) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f))
                IconButton(onClick = { onEditClick(recipe) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { onDeleteClick(recipe.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop, // Adjust as necessary
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
           
        }
    }
}

// Sample data provider function for demonstration
@Composable
fun sampleRecipes(): List<Recipe> {
    return listOf(
        Recipe("1", "Brownie", "Delicious chocolate brownie", painterResource(id = R.drawable.brownie), ""),
        Recipe("2", "Carrot Cake", "Healthy carrot cake", painterResource(id = R.drawable.carrot_cake), ""),
        Recipe("3", "Sweet Almond", "Sweet almond pastry", painterResource(id = R.drawable.sweet_almond), "")
    )
}

// Preview of RecipeListScreen
@Preview(showBackground = true)
@Composable
fun PreviewRecipeListScreen() {
    val navController = rememberNavController()
    RecipeListScreen(navController)
}
