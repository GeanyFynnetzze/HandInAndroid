package com.example.handinv1.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handinv1.Api.Pokemon;
import com.example.handinv1.R;
import com.example.handinv1.RecycleAdapter;
import com.example.handinv1.Api.RepositoryApi;
import com.example.handinv1.Api.ViewModelApi;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private ViewModelApi viewModelApi;
    private RepositoryApi repositoryApi;
    private List<Pokemon> pokemonList;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;
    private RecycleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ArrayList<ItemList> items = new ArrayList<>();
        items.add(new ItemList("Fresh Water", "Restores 30 HP", R.drawable.ic_freshwater));
        items.add(new ItemList("Full Heal", "A spray-type medicine. It heals all the status problems of a single Pokémon.", R.drawable.ic_fullheal));
        items.add(new ItemList("Full Restore", "A medicine that fully restores the HP and heals any status problems of a single Pokémon.", R.drawable.ic_fullrestore));
        items.add(new ItemList("HP UP", "Increases HP EVs by 10.", R.drawable.ic_hp_up));
        items.add(new ItemList("Hyper Potion", "Restores 120 HP.", R.drawable.ic_hyperpotion));
        items.add(new ItemList("Ice Heal", "A spray-type medicine. It defrosts a Pokémon that has been frozen solid.", R.drawable.ic_iceheal));
        items.add(new ItemList("Iron", "Increases Defense EVs by 10.", R.drawable.ic_iron));
        items.add(new ItemList("Lava Cookie", "Heals all major status conditions.", R.drawable.ic_lavacookie));
        items.add(new ItemList("Lemonade", "Restores 70 HP.", R.drawable.ic_lemonade));
        items.add(new ItemList("Lumiose Gallette", "Heals all major status conditions.", R.drawable.ic_lumiosegalette));
        items.add(new ItemList("Max Elixir", "It fully restores the PP of all the moves learned by the targeted Pokémon.", R.drawable.ic_maxelixir));
        items.add(new ItemList("Max Ether", "It fully restores the PP of a single selected move that has been learned by the target Pokémon.", R.drawable.ic_maxether));
        items.add(new ItemList("Max Potion", "Fully restores HP.", R.drawable.ic_maxpotion));
        items.add(new ItemList("Max Revive", "A medicine that revives a fainted Pokémon. It fully restores the Pokémon's HP.", R.drawable.ic_maxrevive));
        items.add(new ItemList("Mighty Candy", "Increases a Pokémon's Attack stat by 1.", R.drawable.ic_mightycandy));
        items.add(new ItemList("Moomoo milk", "Milk with a very high nutrition content. It restores the HP of one Pokémon by 100 points.", R.drawable.ic_mightycandy));
        items.add(new ItemList("Muscle Wing", "Increases Attack EVs by 1.", R.drawable.ic_musclewing));
        items.add(new ItemList("Old Gateau", "Heals all major status conditions.", R.drawable.ic_oldgateau));
        items.add(new ItemList("Paralyze Heal", "A spray-type medicine. It eliminates paralysis from a single Pokémon.", R.drawable.ic_paralyzeheal));
        items.add(new ItemList("Potion", "Restores 20 HP.", R.drawable.ic_potion));
        items.add(new ItemList("PP Max", "It maximally raises the top PP of a selected move that has been learned by the target Pokémon.", R.drawable.ic_ppmax));

         recyclerView = rootView.findViewById(R.id.recyclerview);
         recyclerView.hasFixedSize();
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
         adapter = new RecycleAdapter(getContext(),items);
        recyclerView.setAdapter(adapter);




 return rootView;
    }
}
