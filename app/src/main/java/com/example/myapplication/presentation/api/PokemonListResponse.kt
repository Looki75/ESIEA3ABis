package com.example.myapplication.presentation.api

import com.example.myapplication.presentation.list.Pokemon

data class PokemonListResponse(
        val count: Int,
        val next: String,
        val results: List<Pokemon>

)