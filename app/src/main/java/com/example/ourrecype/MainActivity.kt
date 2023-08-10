package com.example.ourrecype

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ourrecype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recipeAdapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvRecipe.adapter = recipeAdapter

        getRecipes()

        recipeAdapter.setOnItemClickListener {
            val intent = Intent(this, DetailRecipeActivity::class.java)
            intent.putExtra("keyId", it)
            startActivity(intent)
        }

    }

    private fun getRecipes() {
        val retrofit = ApiClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getRecipe()
                if (response.isSuccessful()) {
                    response.body()?.apply {
                        recipeAdapter.differ.submitList(this.results)
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }
    }
}