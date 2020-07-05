package com.example.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Steps;
import com.example.bakingapp.viewholder.StepsListViewHolder;

import java.util.List;


public class StepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private List<Steps> stepsList;
    private Callback callback;

    public StepsAdapter(){}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_steps, parent, false);
        return new StepsListViewHolder(view, callback);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((StepsListViewHolder)viewHolder).bindData(getItemByPosition(position));

    }

    public void setSteps(List<Steps> steps) {
        stepsList = steps;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return stepsList!= null ? stepsList.size():0;
    }

    public Steps getItemByPosition(int position) {
     return stepsList.get(position);
    }

    @Override
    public void onClick(View v) {

    }

    public interface Callback {
        void onListItemClick(int position);
    }


}
