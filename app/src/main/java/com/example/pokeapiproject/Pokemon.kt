package com.example.pokeapiproject

data class Pokemon (
    val id: Int,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val name: String,
    val types: List<Type>
    )


data class Sprites (
    val front_default: String,
)

data class PokemonResponse (
    val results: List<Pokemon>
)

data class Type (
    val slot: Int,
    val type: TypeInformation
)

data class TypeInformation (
    val name: String,
    val url: String
)
