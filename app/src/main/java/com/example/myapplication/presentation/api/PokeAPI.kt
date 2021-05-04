package com.example.myapplication.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeAPI {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonResponse>
}