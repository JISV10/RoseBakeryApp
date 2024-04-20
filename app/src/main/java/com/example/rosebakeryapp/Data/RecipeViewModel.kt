package com.example.rosebakeryapp.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Room
import com.example.rosebakeryapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "recipes").build()
    private val dao = db.recipeDao()
    val recipes: Flow<List<Recipe>> = dao.getAllRecipes()
    private val recipeDao = Room.databaseBuilder(application, AppDatabase::class.java, "recipes-db")
        .build()
        .recipeDao()

    fun getRecipeById(recipeId: String): LiveData<Recipe> {
        return recipeDao.getRecipeById(recipeId).asLiveData()
    }

    init{
        insertSampleRecipes()
    }

    private fun insertSampleRecipes() = runBlocking {
        launch(Dispatchers.IO) {
            val initialRecipes = listOf(
                Recipe("1", "Brownie", "Delicious chocolate brownie", R.drawable.brownie, ""),
                Recipe("2", "Carrot Cake", "Healthy carrot cake", R.drawable.carrot_cake, ""),
                Recipe("3", "Sweet Almond", "Sweet almond pastry", R.drawable.sweet_almond, "")
            )
            dao.insertAll(*initialRecipes.toTypedArray())
        }
    }
}

