package com.example.handinv1.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Api {
    @GET("api/v2/pokemon/{name}")
    Call<PokemonResponse> getPokemon(@Path("name") String name);
}
