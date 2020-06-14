package com.example.handinv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handinv1.View.ItemList;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyviewHolder> {
    private ArrayList<ItemList> items;


    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_adapter, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyviewHolder holder, int position) {
       holder.itemImage.setImageResource(items.get(position).getIconId());
       holder.itemName.setText(items.get(position).getItemName());
       holder.itemDescription.setText(items.get(position).getItemEffect());
    }

    public RecycleAdapter(Context context, ArrayList<ItemList> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    static class MyviewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemDescription;


        public MyviewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imageview);
            itemName = itemView.findViewById(R.id.textview1);
            itemDescription = itemView.findViewById(R.id.textview2);

        }

    }
}



