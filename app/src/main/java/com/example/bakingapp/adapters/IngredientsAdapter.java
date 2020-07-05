package com.example.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.viewholder.IngredientsListViewHolder;

import java.util.List;


public class IngredientsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private List<Ingredients> ingredientsList;
    private Callback callback;

    public IngredientsAdapter(){}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_ingredient, parent, false);
        return new IngredientsListViewHolder(view, callback);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((IngredientsListViewHolder)viewHolder).bindData(getItemByPosition(position));

    }

    public void setIngredients(List<Ingredients> ingredients) {
        ingredientsList = ingredients;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return ingredientsList!= null ? ingredientsList.size():0;
    }

    public Ingredients getItemByPosition(int position) {
     return ingredientsList.get(position);
    }

    @Override
    public void onClick(View v) {

    }

    public interface Callback {
        void onListItemClick(int position);
    }


}
