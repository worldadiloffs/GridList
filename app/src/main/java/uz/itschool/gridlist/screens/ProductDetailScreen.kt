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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itschool.gridlist.R
import uz.itschool.gridlist.model.Product

@Composable
fun ProductDetailScreen(
    productName: String,
    productDescription: String,
    imageResId: Int,
    price: Double,
    likeState: MutableState<Boolean>,
    onLikeChanged: (Boolean) -> Unit,
    rating: Float

) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray) // Change the color as needed
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
    {
        Column(
            modifier = Modifier

                .padding(16.dp)
        ) {
            ProductImageWithLikeButton(imageResId = imageResId, likeState, onLikeChanged)

            Spacer(modifier = Modifier.height(16.dp))

            ProductDetails(
                productName = productName,
                productDescription = productDescription,
                price = price,
                rating = rating
            )
        }
    }
}

@Composable
fun ProductItem(
    product: Product, likeState: MutableState<Boolean>,
    onLikeChanged: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductImageWithLikeButton(imageResId = product.imageUrl, likeState, onLikeChanged)

            Spacer(modifier = Modifier.height(16.dp))

            ProductDetails(
                productName = product.title,
                productDescription = product.description,
                price = product.price,
                rating = product.rating
            )
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, onItemClick: (Product) -> Unit) {
    val likeState = remember {
        products.map { product -> mutableStateOf(false) }
    }
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Adaptive(128.dp),
        content = {
            items(products.size) {index->

                ProductItem(
                    product = products[index],
                    likeState = likeState[index],
                    onLikeChanged = { isLiked ->
                        likeState[index].value = isLiked
                    },
//                    onItemClick = onItemClick
                )
            }
        })

}

@Composable
fun ProductList(
    products: List<Product>, likeState: MutableState<Boolean>,
    onLikeChanged: (Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductItem(product = product, likeState, onLikeChanged)
            Divider() // Add a divider between items if needed
        }
    }
}

@Composable
fun ProductImageWithLikeButton(
    imageResId: Int, likeState: MutableState<Boolean>,
    onLikeChanged: (Boolean) -> Unit
) {
    Box {
        val image: Painter = painterResource(id = imageResId)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        LikeButton(modifier = Modifier.align(Alignment.TopEnd), likeState, onLikeChanged)
    }
}

@Composable
fun LikeButton(
    modifier: Modifier = Modifier, likeState: MutableState<Boolean>,
    onLikeChanged: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .size(48.dp)
    ) {
        IconButton(onClick = { onLikeChanged(!likeState.value) }) {
            Icon(
                imageVector = if (likeState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Like Button",
                tint = Color.Red
            )
        }
    }
}

@Composable
fun ProductDetails(
    productName: String,
    productDescription: String,
    price: Double,
    rating: Float

) {
    Column {
        Text(
            text = productName,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = productDescription,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    append("$")
                    pushStyle(
                        SpanStyle(
                            color = Color.Blue, // Change color as needed
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                    append("$price")
                    pop()
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "$rating",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray // Change color as needed
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}



