package com.example.bakingapp.viewholder;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.StepsAdapter;
import com.example.bakingapp.model.Steps;


public class StepsListViewHolder extends RecyclerView.ViewHolder {
    private TextView stepsNameTV;

    public StepsListViewHolder(@NonNull View itemView, final StepsAdapter.Callback callback) {
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
        stepsNameTV = (TextView)itemView.findViewById(R.id.stepNameTV);
    }

    public void bindData(Steps steps) {
        stepsNameTV.setText(steps.getShortDescription());

    }
}
