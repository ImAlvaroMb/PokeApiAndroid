package com.example.pokeapiproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapiproject.databinding.ActivityPokemondetailBinding
import com.squareup.picasso.Picasso


class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemondetailBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemondetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("idpokemon", 0)
        val name = intent.getStringExtra("name")
        val weight = intent.getIntExtra("weight", 0)
        val height = intent.getIntExtra("height", 0)
        val types = intent.getStringArrayListExtra("types")

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png").into(binding.pokemonImage)
        binding.textPokemonName.text = name;
        binding.weight.text = "Weight: $weight"
        binding.height.text = "Height; $height"
        println(types)
        binding.textView4.text = "Types: $types"


        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

