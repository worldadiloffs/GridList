package uz.itschool.gridlist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import uz.itschool.gridlist.R
import uz.itschool.gridlist.model.Product

@Composable
fun MainScreen(navController: NavController) {
    val products = listOf(
        Product("Rose", "Flower", R.drawable.rose, 8.3, 5f),
        Product("Product 1", "Feature 1", R.drawable.rose, 8.3, 5f),
        Product("Product 2", "Feature 3", R.drawable.rose, 8.3, 4f),
        Product("Product 3", "Feature 5", R.drawable.rose, 15.5, 4.5f),
        Product("Product 4", "Feature 7", R.drawable.rose, 10.5, 3.5f),
        Product("Product 4", "Feature 7", R.drawable.rose, 10.5, 3.5f),
        Product("Product 4", "Feature 7", R.drawable.rose, 10.5, 3.5f)
    )
    ProductGrid(products = products, onItemClick = {navController.navigate("more_screen/${products}")})
}

