package com.example.ourrecype

data class RecipesModel(
    val offset: Int? = null,
    val number: Int? = null,
    val results: List<Results>? = null
) {
    data class Results(
        val id: Int,
        val title: String,
        val image: String
    )
}