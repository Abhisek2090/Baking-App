package com.example.bakingapp.viewholder;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.ReceipesAdapter;
import com.example.bakingapp.model.Receipe;


public class ReceipesListViewHolder extends RecyclerView.ViewHolder {
    private TextView receipeNameTV;

    public ReceipesListViewHolder(@NonNull View itemView, final ReceipesAdapter.Callback callback) {
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
        receipeNameTV = (TextView)itemView.findViewById(R.id.receipeNameTV);
    }

    public void bindData(Receipe receipe) {
            receipeNameTV.setText(receipe.getName());

    }
}
