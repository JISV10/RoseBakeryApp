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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rosebakeryapp.Data.Recipe
import com.example.rosebakeryapp.Data.RecipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(navController: NavController) {
    val viewModel: RecipeViewModel = viewModel()
    val recipes by viewModel.recipes.collectAsState(initial = emptyList())

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
            onEditRecipeClick = { /* Handle edit */ }
        ) { recipeId ->
            navController.navigate("recipeView/$recipeId")
        }
    }
}



@Composable
//fun RecipeList(
//    recipes: com.example.rosebakeryapp.Data.Recipe,
//    modifier: Modifier = Modifier,
//    onDeleteRecipeClick: (String) -> Unit,
//    onEditRecipeClick: (Recipe) -> Unit,
//    onItemClick: (String) -> Unit
//) {
//    LazyColumn(modifier = modifier) {
//        items(recipes) { recipe ->
//            RecipeItem(
//                recipe = recipe,
//                onDeleteClick = onDeleteRecipeClick,
//                onEditClick = onEditRecipeClick,
//                onItemClick = onItemClick
//            )
//        }
//    }
//}
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
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = recipe.imageUrl),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop, // Adjust as necessary
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { onEditClick(recipe) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { onDeleteClick(recipe.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

// Sample data provider function for demonstration
//@Composable
//fun sampleRecipes(): List<Recipe> {
//    return listOf(
//        Recipe("1", "Brownie", "Delicious chocolate brownie", painterResource(id = R.drawable.brownie), ""),
//        Recipe("2", "Carrot Cake", "Healthy carrot cake", painterResource(id = R.drawable.carrot_cake), ""),
//        Recipe("3", "Sweet Almond", "Sweet almond pastry", painterResource(id = R.drawable.sweet_almond), "")
//    )
//}

// Preview of RecipeListScreen
@Preview(showBackground = true)
@Composable
fun PreviewRecipeListScreen() {
    val navController = rememberNavController()
    RecipeListScreen(navController)
}
