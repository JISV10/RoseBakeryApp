package com.example.rosebakeryapp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosebakeryapp.ui.theme.RoseBakeryAppTheme

// Array de ingredientes básicos de un brownie
val brownieIngredients = listOf(
    Pair("Unsalted butter", "1 stick"),
    Pair("White sugar", "1 cup"),
    Pair("Cocoa powder", "3/4 cup"),
    Pair("Large eggs", "2"),
    Pair("Vanilla extract", "1 teaspoon"),
    Pair("Flour", "1/2 cup"),
    Pair("Salt", "1/4 teaspoon"),
    Pair("Chocolate chips", "1/2 cups")
)

@Composable
fun ShopARecipe() {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(brownieIngredients) { ingredient ->
            IngredientCard(ingredientName = ingredient.first, quantity = ingredient.second)
        }
    }
}

@Composable
fun IngredientCard(ingredientName: String, quantity: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = ingredientName, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = quantity, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopARecipePreview() {
    RoseBakeryAppTheme {
        ShopARecipe()
    }
}

