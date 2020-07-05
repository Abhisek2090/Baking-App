package com.example.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bakingapp.R;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.viewholder.ReceipesListViewHolder;

import java.util.List;


public class ReceipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private List<Receipe> receipeList;
    private Callback callback;

    public ReceipesAdapter(){}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_receipe, parent, false);
        return new ReceipesListViewHolder(view, callback);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((ReceipesListViewHolder)viewHolder).bindData(getItemByPosition(position));

    }

    public void setReceipes(List<Receipe> receipes) {
        receipeList = receipes;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return receipeList!= null ? receipeList.size():0;
    }

    public Receipe getItemByPosition(int position) {
     return receipeList.get(position);
    }

    @Override
    public void onClick(View v) {

    }

    public interface Callback {
        void onListItemClick(int position);
    }


}
