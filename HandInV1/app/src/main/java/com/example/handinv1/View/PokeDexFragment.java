package com.example.handinv1.View;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.handinv1.Api.Pokemon;
import com.example.handinv1.R;
import com.example.handinv1.Api.ViewModelApi;

public class PokeDexFragment extends Fragment {


    EditText editText;
    ImageView imageView;
    ViewModelApi viewModel;
    TextView name;
    TextView height;
    Button pokedex;
    TextView weight;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pokedex, container, false);
        editText = rootView.findViewById(R.id.editText);
        imageView = rootView.findViewById(R.id.imageView123);
        name = rootView.findViewById(R.id.nameView);
        pokedex= rootView.findViewById(R.id.button);
        height = rootView.findViewById(R.id.heightView);
        weight = rootView.findViewById(R.id.weightView);
        pokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePokemon(rootView);
            }
        });
        viewModel = new ViewModelProvider(this).get(ViewModelApi.class);
        viewModel.getPokemon().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                Glide.with(PokeDexFragment.this).load(pokemon.getImageUrl()).into(imageView);
                name.setText(pokemon.getName());
                height.setText(Integer.toString(pokemon.getHeight()) + " Dm");
                weight.setText(Integer.toString(pokemon.getWeight())+ " Hg");

            }
        });



            return rootView;
        }


        public void updatePokemon(View view) {
            viewModel.updatePokemon(editText.getText().toString());
        }
    }


