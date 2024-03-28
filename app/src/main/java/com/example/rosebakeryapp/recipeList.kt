package com.example.rosebakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class RecipeList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val recipes = listOf(
                Recipe("1", "Brownie", "Description of recipe 1", painterResource(id = R.drawable.brownie)),
                Recipe("2", "Carrot Cake", "Description of recipe 2", painterResource(id = R.drawable.carrot_cake)),
                Recipe("3", "Sweet Almond", "Description of recipe 3", painterResource(id = R.drawable.sweet_almond))
            )
            MainAppRecipes(
                recipes,
                onAddRecipe = { title, description -> /* code to add recipe */ },
                onDeleteRecipeClick = { id -> /* code to delete recipe */ },
                onEditRecipeClick = { recipe -> /* code to edit recipe */ }
            )
        }
    }
}

data class Recipe(val id: String, val title: String, val description: String, val image: Painter)

@Composable
fun RecipeItem(
    recipe: Recipe,
    onDeleteClick: (String) -> Unit,
    onEditClick: (Recipe) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { onEditClick(recipe) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(
                    onClick = { onDeleteClick(recipe.id) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = recipe.image,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp).
                    clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onDeleteRecipeClick: (String) -> Unit,
    onEditRecipeClick: (Recipe) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(56.dp))
        LazyColumn {
            items(recipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onDeleteClick = onDeleteRecipeClick,
                    onEditClick = onEditRecipeClick
                )
            }
        }
    }
}


@Composable
fun AddRecipeDialog(
    onAddRecipe: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var newRecipeTitle by remember { mutableStateOf("") }
    var newRecipeDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Add Recipe") },
        text = {
            Column {
                TextField(
                    value = newRecipeTitle,
                    onValueChange = { newRecipeTitle = it },
                    label = { Text("Title") }
                )
                TextField(
                    value = newRecipeDescription,
                    onValueChange = { newRecipeDescription = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onAddRecipe(newRecipeTitle, newRecipeDescription) }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditRecipeDialog(
    recipe: Recipe,
    onEditRecipeSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var editRecipeTitle by remember { mutableStateOf(recipe.title) }
    var editRecipeDescription by remember { mutableStateOf(recipe.description) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Edit Recipe") },
        text = {
            Column {
                TextField(
                    value = editRecipeTitle,
                    onValueChange = { editRecipeTitle = it },
                    label = { Text("Title") }
                )
                TextField(
                    value = editRecipeDescription,
                    onValueChange = { editRecipeDescription = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onEditRecipeSave(editRecipeTitle, editRecipeDescription) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DeleteRecipeDialog(
    onConfirmDelete: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Delete Recipe") },
        text = { Text("Are you sure you want to delete this recipe?") },
        confirmButton = {
            Button(onClick = onConfirmDelete) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun MyAppRecipes(
    recipes: List<Recipe>,
    onAddRecipe: (String, String) -> Unit,
    onDeleteRecipeClick: (String) -> Unit,
    onEditRecipeClick: (Recipe) -> Unit
) {
    var isAddDialogOpen by remember { mutableStateOf(false) }
    var isEditDialogOpen by remember { mutableStateOf(false) }
    var isDeleteDialogOpen by remember { mutableStateOf(false) }
    var recipeToDelete: String by remember { mutableStateOf("") }
    var recipeToEdit: Recipe? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("My Recipes") },
                actions = {
                    IconButton(onClick = { isAddDialogOpen = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Recipe")
                    }
                }
            )
        }
    ) {
        Column {
            RecipeList(
                recipes = recipes,
                onDeleteRecipeClick = { id ->
                    recipeToDelete = id
                    isDeleteDialogOpen = true
                },
                onEditRecipeClick = { recipe ->
                    recipeToEdit = recipe
                    isEditDialogOpen = true
                }
            )

            if (isAddDialogOpen) {
                AddRecipeDialog(
                    onAddRecipe = { title, description ->
                        onAddRecipe(title, description)
                        isAddDialogOpen = false
                    },
                    onCancel = { isAddDialogOpen = false }
                )
            }

            recipeToEdit?.let { recipe ->
                EditRecipeDialog(
                    recipe = recipe,
                    onEditRecipeSave = { title, description ->
                        onEditRecipeClick(recipe.copy(title = title, description = description))
                        isEditDialogOpen = false
                    },
                    onCancel = { isEditDialogOpen = false }
                )
            }

            if (isDeleteDialogOpen) {
                DeleteRecipeDialog(
                    onConfirmDelete = {
                        onDeleteRecipeClick(recipeToDelete)
                        isDeleteDialogOpen = false
                    },
                    onCancel = { isDeleteDialogOpen = false }
                )
            }
        }
    }
}

@Composable
fun MainAppRecipes(
    recipes: List<Recipe>,
    onAddRecipe: (String, String) -> Unit,
    onDeleteRecipeClick: (String) -> Unit,
    onEditRecipeClick: (Recipe) -> Unit
) {
    MaterialTheme {
        MyAppRecipes(
            recipes,
            onAddRecipe,
            onDeleteRecipeClick,
            onEditRecipeClick
        )
    }
}

@Preview
@Composable
fun PreviewRecipeList() {
    val recipes = listOf(
        Recipe("1", "Brownie", "Description of recipe 1", painterResource(id = R.drawable.brownie)),
        Recipe("2", "Carrot Cake", "Description of recipe 2", painterResource(id = R.drawable.carrot_cake)),
        Recipe("2", "Carrot Cake", "Description of recipe 2", painterResource(id = R.drawable.carrot_cake)),
        Recipe("3", "Sweet Almond", "Description of recipe 3", painterResource(id = R.drawable.sweet_almond))
    )
    MainAppRecipes(
        recipes,
        onAddRecipe = { title, description -> /* code to add recipe */ },
        onDeleteRecipeClick = { id -> /* code to delete recipe */ },
        onEditRecipeClick = { recipe -> /* code to edit recipe */ }
    )
}
