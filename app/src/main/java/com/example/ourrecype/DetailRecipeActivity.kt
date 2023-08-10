package com.example.ourrecype

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.ourrecype.databinding.ActivityDetailRecipeBinding
import com.example.ourrecype.databinding.ActivityMainBinding

class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipeBinding

    private val ingredientsAdapter = IngredientsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvIngredients.adapter = ingredientsAdapter

        val idRecipe = intent.extras?.getInt("keyId") ?: 0

        getDetailRecipe(idRecipe)

    }

    private fun getDetailRecipe(id: Int) {
        val retrofit = ApiClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getDetailRecipe(id)
                if (response.isSuccessful()) {
                    response.body()?.apply {
                        val item = this
                        binding.apply {
                            Glide.with(root.context)
                                .load(item.image)
                                .into(ivRecipe)
                            tvTitle.text = item.title
                            tvServings.text =  "Servings : ${item.servings}"
                            tvServingMinutes.text = "Ready In : ${item.readyInMinutes} Minutes"
                            ingredientsAdapter.differ.submitList(item.extendedIngredients)
                        }
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