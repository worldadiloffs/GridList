package uz.itschool.gridlist.model

import java.io.Serializable

data class Product(
    val title: String,
    val description: String,
    val imageUrl: Int,
    val price: Double,
    val rating: Float
):Serializable