package com.example.bakingapp.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeRepository;
import com.example.bakingapp.fragments.StepDetailsFragment;
import com.example.bakingapp.fragments.StepsVideoFragment;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.model.Steps;
import com.example.bakingapp.utils.Constants;
import com.example.bakingapp.utils.GsonInstance;

public class StepDetailsActivity extends AppCompatActivity {
    private String uri;
    private String description;
    private Button nextButton, backButton;
    int position = 0;
    private Receipe recipeData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        backButton =(Button)findViewById(R.id.backButton);
        nextButton =(Button)findViewById(R.id.nextButton);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.steps);
        }


        Steps steps = (Steps)getIntent().getSerializableExtra(Constants.STEPS);
        if (steps != null) {
            uri = steps.getVideoURL();
            description = steps.getDescription();
            position = steps.getId();
        }

        initializeDescriptionFragment(description);
        initializeVideoFragment(uri);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                recipeData = GsonInstance.getGsonInstance().
                        fromJson(RecipeRepository.getInstance().
                                getRecipe(position, StepDetailsActivity.this), Receipe.class);

                if(position<recipeData.getSteps().size() && position >= 0) {
                    initializeVideoFragment(recipeData.getSteps().get(position).getVideoURL());
                    initializeDescriptionFragment(recipeData.getSteps().get(position).getDescription());
                }


            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                recipeData = GsonInstance.getGsonInstance().
                        fromJson(RecipeRepository.getInstance().
                                getRecipe(position, StepDetailsActivity.this), Receipe.class);
                if(position<recipeData.getSteps().size() && position >= 0) {
                    initializeVideoFragment(recipeData.getSteps().get(position).getVideoURL());
                    initializeDescriptionFragment(recipeData.getSteps().get(position).getDescription());
                }



            }
        });



    }

    void initializeVideoFragment(String uri) {
        StepsVideoFragment stepsVideoFragment =  StepsVideoFragment.newInstance(uri);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.stepVideoContainer, stepsVideoFragment)
                .commit();
    }

    void initializeDescriptionFragment(String description) {
        StepDetailsFragment stepDetailsFragment =  StepDetailsFragment.newInstance(description);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.stepsDescriptionContainer, stepDetailsFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}