package com.example.myapplication.presentation.api

import retrofit2.Call
import retrofit2.http.GET


interface PokeApi {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonListResponse>
}