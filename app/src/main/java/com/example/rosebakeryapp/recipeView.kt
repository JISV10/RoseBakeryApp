package com.example.rosebakeryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeView(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = recipe.image,
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = recipe.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun RecipeViewPreview() {
    RecipeView(
        recipe = Recipe(
            id = "1",
            title = "Brownie",
            description = "140 grams dark chocolate\n" +
                    "80 grams butter\n" +
                    "2 large eggs\n" +
                    "1 cup white sugar\n" +
                    "1/2 cup all-purpose flour\n" +
                    "1/2 teaspoon salt\n" +
                    "1 tablespoon vanilla extract\n" +
                    "Now, here's a brief instruction for making a chocolate cake:\n" +
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
    )
}
