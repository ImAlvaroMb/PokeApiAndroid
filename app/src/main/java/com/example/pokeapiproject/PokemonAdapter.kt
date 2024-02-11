package com.example.pokeapiproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.pokeapiproject.databinding.ItemPokemonBinding

class PokemonAdapter(private val context: Context, private val pokemon: List<Pokemon>) :
RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemon[position]


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png").into(holder.binding.imageViewPokemon)
        holder.binding.textPokemonName.text = pokemon.name;

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra("idpokemon", pokemon.id)
            intent.putExtra("weight", pokemon.weight)
            intent.putExtra("height", pokemon.height)
            intent.putExtra("name", pokemon.name)
            val types = pokemon.types.map { it.type.name }
            //println(types)
            intent.putStringArrayListExtra("types", ArrayList(types))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }


    class PokemonViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
}