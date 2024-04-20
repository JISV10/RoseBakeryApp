package com.example.rosebakeryapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "recipes")
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: Int,
    val ingredients: String
)

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    fun getRecipeById(recipeId: String): Flow<Recipe>

    @Insert
    fun insertRecipe(recipe: Recipe)
    fun insertAll(vararg recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)


}
