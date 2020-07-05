package com.example.bakingapp.viewholder;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.IngredientsAdapter;
import com.example.bakingapp.model.Ingredients;


public class IngredientsListViewHolder extends RecyclerView.ViewHolder {
    private TextView quantityTV;
    private TextView measureTV;
    private TextView ingredientTV;

    public IngredientsListViewHolder(@NonNull View itemView, final IngredientsAdapter.Callback callback) {
        super(itemView);
        Context context = itemView.getContext();
        initViews(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onListItemClick(getAdapterPosition());
                }
            }
        });
    }

    private void initViews(View itemView) {
        quantityTV = (TextView)itemView.findViewById(R.id.quantityTV);
        measureTV = (TextView)itemView.findViewById(R.id.measureTV);
        ingredientTV = (TextView)itemView.findViewById(R.id.ingredientTV);
    }

    public void bindData(Ingredients ingredients) {

        quantityTV.setText(String.valueOf(ingredients.getQuantity()));
        measureTV.setText(String.valueOf(ingredients.getMeasure()));
        ingredientTV.setText(String.valueOf(ingredients.getIngredient()));


    }
}
