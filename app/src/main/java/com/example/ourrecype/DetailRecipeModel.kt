package com.example.ourrecype

data class DetailRecipeModel(
    val id: Int? = null,
    val title: String? = null,
    val image: String? = null,
    val servings: Int? = null,
    val readyInMinutes: Int? = null,
    val extendedIngredients: List<Ingredients>? = null
) {
    data class Ingredients(
        val amount: Float? = null,
        val unit: String? = null,
        val name: String? = null
    )
}