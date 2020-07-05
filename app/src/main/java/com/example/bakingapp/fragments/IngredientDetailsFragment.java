package com.example.bakingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.IngredientsAdapter;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.widget.RecipeWidget;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "receipe";
    private RecyclerView ingredientsRecyclerView;
    private IngredientsAdapter ingredientsAdapter;
    private Receipe receipe;
    private Button addToWidgetButton;
    public IngredientDetailsFragment() {
        // Required empty public constructor
    }


    public static IngredientDetailsFragment newInstance(Receipe receipe) {
        IngredientDetailsFragment fragment = new IngredientDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, receipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            receipe = (Receipe) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredient_details, container, false);
        addToWidgetButton = (Button)rootView.findViewById(R.id.addToWidgetButton);
        addToWidgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeWidget.selectRecipe(receipe.getId(), getContext());
                Toast.makeText(getContext(), R.string.add_to_homescreen, Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ingredientsRecyclerView = rootView.findViewById(R.id.ingredientsRecyclerView);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);

        ingredientsAdapter = new IngredientsAdapter();
        ingredientsAdapter.setIngredients(receipe.getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        return rootView;
    }




}