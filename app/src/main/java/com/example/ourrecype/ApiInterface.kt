package com.example.ourrecype

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("complexSearch?number=20&apiKey=092d9d0636d34d41bdadfbdc6b09f435")
    suspend fun getRecipe(): Response<RecipesModel>

    @GET("{id}/information?includeNutrition=false&apiKey=092d9d0636d34d41bdadfbdc6b09f435")
    suspend fun getDetailRecipe(
        @Path("id") id: Int
    ): Response<DetailRecipeModel>

}