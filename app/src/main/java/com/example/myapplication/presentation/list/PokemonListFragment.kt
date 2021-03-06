package com.example.myapplication.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation.Singletons
import com.example.myapplication.presentation.api.PokeApi
import com.example.myapplication.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.pokemon_recyclerview)
        loader = view.findViewById(R.id.pokemon_loader)
        textViewError = view.findViewById(R.id.pokemon_error)

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PokemonListFragment.adapter
        }

        viewModel.pokeList.observe(viewLifecycleOwner, Observer { pokemonModel ->

            loader.isVisible = pokemonModel is PokemonLoader
            textViewError.isVisible = pokemonModel is PokemonError

            if(pokemonModel is PokemonSuccess) {
                adapter.updateList(pokemonModel.pokeList)
            }


        })

    }

    private fun onClickedPokemon(id: Int) {

        findNavController().navigate(
            R.id.navigateToPokemonDetailFragment, bundleOf(
                "pokemonId" to (id + 1)
            )
        )
    }
}
