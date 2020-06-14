package com.example.handinv1.Api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelApi extends ViewModel {

    private RepositoryApi repository;

    public ViewModelApi(){
        repository = RepositoryApi.getInstance();
    }

    public LiveData<Pokemon> getPokemon() {
        return repository.getPokemon();
    }

    public void updatePokemon(String poke) {
        repository.updatePokemon(poke);
    }

}
