package com.example.handinv1.Api;

public class PokemonResponse {
    private int id;
    private String name;
    private int height;
    private int weight;
    private Sprites sprites;

    public Pokemon getPokemon(){
        return new Pokemon(id,name,height,weight,sprites.front_default);
    }

    private class Sprites {
        private String front_default;
    }
}
