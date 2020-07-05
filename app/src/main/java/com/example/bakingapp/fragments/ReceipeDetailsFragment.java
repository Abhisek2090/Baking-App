package com.example.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeRepository;
import com.example.bakingapp.activities.StepDetailsActivity;
import com.example.bakingapp.utils.Constants;
import com.example.bakingapp.adapters.StepsAdapter;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.model.Steps;
import com.example.bakingapp.utils.GsonInstance;

public class ReceipeDetailsFragment extends Fragment {

    private RecyclerView stepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private TextView ingredientsListTV;
    private Button nextButton, backButton;
    private boolean mTwoPane;
    private static int mPosition =0;
    private Receipe recipeData;

    private static final String TAG= ReceipeDetailsFragment.class.getSimpleName();
    private Receipe receipe;

    ReceipeDetailsInterface receipeDetailsInterfaceCallback;

    public ReceipeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(getArguments() != null) {
            receipe = (Receipe) getArguments().getSerializable(Constants.RECEIPE);
            mTwoPane = getArguments().getBoolean(Constants.PANE, false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_receipe_details, container, false);
        backButton =(Button)rootView.findViewById(R.id.backButton);
        nextButton =(Button)rootView.findViewById(R.id.nextButton);
        if(mTwoPane) {
            backButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition--;


                    if(mPosition<receipe.getSteps().size() && mPosition >= 0) {
                        receipeDetailsInterfaceCallback.showStepDetails(stepsAdapter.getItemByPosition(mPosition));
                    }

                }
            });

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition++;

                    if(mPosition < receipe.getSteps().size() && mPosition >= 0) {
                        receipeDetailsInterfaceCallback.showStepDetails(stepsAdapter.getItemByPosition(mPosition));
                    }

                }
            });
        }
        else {
            backButton.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
        }
        ingredientsListTV = (TextView)rootView.findViewById(R.id.ingredientsListTV);
        ingredientsListTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receipeDetailsInterfaceCallback.showIngredientsFragments();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView = (RecyclerView)rootView.findViewById(R.id.stepsRecyclerView);
        stepsRecyclerView.setLayoutManager(linearLayoutManager);
        stepsAdapter = new StepsAdapter();
        stepsRecyclerView.setAdapter(stepsAdapter);
        stepsAdapter.setSteps(receipe.getSteps());

        stepsAdapter.setCallback(new StepsAdapter.Callback() {
            @Override
            public void onListItemClick(int position) {
                receipeDetailsInterfaceCallback.showStepDetails(stepsAdapter.getItemByPosition(position));
                mPosition = position;
            }
        });

        return rootView;
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            receipeDetailsInterfaceCallback = (ReceipeDetailsInterface) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement interface");
        }
    }

    public interface ReceipeDetailsInterface {
        void showIngredientsFragments();
        void showStepDetails(Steps steps);
    }

}