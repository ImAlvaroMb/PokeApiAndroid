package com.example.pokeapiproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import  com.example.pokeapiproject.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var recyclerView: RecyclerView
    private val pokemons = mutableListOf<Pokemon>()
    private var currentLowerLimit = 1
    private var currectUpperLimit = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            fetchPokemonsInRange(currentLowerLimit, currectUpperLimit )
        }

    }

    private fun fetchPokemonsInRange(loweLimit: Int, upperLimit: Int) {
            GlobalScope.launch(Dispatchers.IO) {
                for(i in loweLimit..upperLimit) {
                    val response = fetchPokemonByNumber(i)
                    launch(Dispatchers.Main) {
                        if(response != null) {
                            parsePokemons(response)
                        }
                    }
                }
            }
    }


    private fun fetchPokemonByNumber(number: Int) : String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://pokeapi.co/api/v2/pokemon/$number/")
            .get()
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        return responseBody
    }


    private fun parsePokemons(response: String) {
        try {
            val gson = Gson()
            val pokemonResponse = gson.fromJson(response, Pokemon::class.java)

            if(pokemonResponse != null) {
                pokemons.add(pokemonResponse)
                initRecyclerView()
            } else {
                println("No files found on response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private  fun initRecyclerView() {
        adapter = PokemonAdapter(this, pokemons)
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}