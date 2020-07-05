package com.example.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bakingapp.R;
import com.example.bakingapp.utils.Constants;
import com.example.bakingapp.fragments.IngredientDetailsFragment;
import com.example.bakingapp.fragments.ReceipeDetailsFragment;
import com.example.bakingapp.fragments.StepDetailsFragment;
import com.example.bakingapp.fragments.StepsVideoFragment;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.model.Steps;

public class ReceipeDetailsActivity extends AppCompatActivity implements ReceipeDetailsFragment.ReceipeDetailsInterface{

    private boolean mTwoPane;
    private Receipe receipe;
    private LinearLayout receipeDetailLL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_details);
        receipeDetailLL = (LinearLayout)findViewById(R.id.receipeDetailLL);

        receipe = (Receipe) getIntent().getSerializableExtra(Constants.RECEIPE);

        if(isTablet(this)) {
            mTwoPane = true;
        }
        else {
            mTwoPane = false;
            receipeDetailLL.setVisibility(View.GONE);
        }

        populateReceipeSteps(receipe, mTwoPane);
    }



    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    @Override
    public void showIngredientsFragments() {
        if(mTwoPane) {
            findViewById(R.id.ingredientDetailsFragmentContainer).setVisibility(View.VISIBLE);
            IngredientDetailsFragment ingredientDetailsFragment =IngredientDetailsFragment.newInstance(receipe);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.ingredientDetailsFragmentContainer, ingredientDetailsFragment)
                    .commit();
        }

        else {
            Intent intent = new Intent(this, IngredientsActivity.class);
            intent.putExtra(Constants.RECEIPE, receipe);
            startActivity(intent);

        }

    }

    @Override
    public void showStepDetails(Steps steps) {
        if(mTwoPane) {

            populateStepsDescription(steps);
            populateStepsVideo(steps);
            findViewById(R.id.ingredientDetailsFragmentContainer).setVisibility(View.GONE);
        }
        else {
            startStepDetailsActivity(steps);
        }

    }

    void populateReceipeSteps(Receipe receipe, boolean mTwoPane) {
        ReceipeDetailsFragment receipeDetailsFragment = new ReceipeDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.RECEIPE, receipe);
        bundle.putBoolean(Constants.PANE, mTwoPane);
        receipeDetailsFragment.setArguments(bundle);


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.receipeDetailsFragmentContainer, receipeDetailsFragment)
                .commit();
    }

    void populateStepsVideo(Steps steps) {
       StepsVideoFragment stepsVideoFragment = StepsVideoFragment.newInstance(steps.getVideoURL());

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.videoStepsFragmentContainer, stepsVideoFragment)
                .commit();
    }

    void populateStepsDescription( Steps steps) {
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(steps.getDescription());

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.stepDetailsFragmentContainer, stepDetailsFragment)
                .commit();
    }

    void startStepDetailsActivity(Steps steps) {
        Intent intent = new Intent(this, StepDetailsActivity.class);
        intent.putExtra(Constants.STEPS, steps);
        startActivity(intent);
    }
}