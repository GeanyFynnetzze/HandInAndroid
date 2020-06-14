package com.example.handinv1.Api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryApi {

    private static RepositoryApi instance;
    private Api api;
    private MutableLiveData<Pokemon> pokemon;


    private RepositoryApi() {
        Retrofit retro = new Retrofit.Builder().baseUrl("https://pokeapi.co").addConverterFactory(GsonConverterFactory.create()).build();
        api = retro.create(Api.class);
        pokemon = new MutableLiveData<>();
    }

    public static synchronized RepositoryApi getInstance() {
        if (instance == null) {
            instance = new RepositoryApi();
        }
        return instance;
    }
    public LiveData<Pokemon> getPokemon() {
        return pokemon;
    }

    public void updatePokemon(String pokemonName) {
        Api pokemonApi = ServiceGenerator.getPokemon();
        Call<PokemonResponse> call = pokemonApi.getPokemon(pokemonName);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()){
                    pokemon.setValue(response.body().getPokemon());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}

