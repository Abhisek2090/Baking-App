package com.example.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.bakingapp.R;
import com.example.bakingapp.utils.Constants;
import com.example.bakingapp.fragments.IngredientDetailsFragment;
import com.example.bakingapp.model.Receipe;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);


      IngredientDetailsFragment ingredientDetailsFragment =  IngredientDetailsFragment.newInstance((Receipe) getIntent().getSerializableExtra(Constants.RECEIPE));


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingredientDetailsFragmentContainer, ingredientDetailsFragment)
                .commit();
    }
}