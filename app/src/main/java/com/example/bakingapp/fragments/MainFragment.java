package com.example.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.Api;
import com.example.bakingapp.R;
import com.example.bakingapp.RecipeRepository;
import com.example.bakingapp.activities.ReceipeDetailsActivity;
import com.example.bakingapp.utils.Constants;
import com.example.bakingapp.adapters.ReceipesAdapter;
import com.example.bakingapp.model.Receipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

public class MainFragment extends Fragment {

    private RecyclerView receipesRecyclerView;
    private ReceipesAdapter receipesAdapter;

    private static final String TAG= MainFragment.class.getSimpleName();
    private List<Receipe> result = new ArrayList<>();
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        result = getReceipes();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        receipesRecyclerView = rootView.findViewById(R.id.receipesRecyclerView);
        if(isTablet(getContext())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            receipesRecyclerView.setLayoutManager(gridLayoutManager);
        }
        else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            receipesRecyclerView.setLayoutManager(linearLayoutManager);
        }

        receipesAdapter = new ReceipesAdapter();
        receipesRecyclerView.setAdapter(receipesAdapter);

        receipesAdapter.setCallback(new ReceipesAdapter.Callback() {
            @Override
            public void onListItemClick(int position) {
                Intent intent = new Intent(getContext(), ReceipeDetailsActivity.class);
                intent.putExtra(Constants.RECEIPE, receipesAdapter.getItemByPosition(position));
                startActivity(intent);
            }
        });
        return rootView;
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private List<Receipe> getReceipes() {
        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create()) //Here we are using the JacksonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        Call<List<Receipe>> call = api.getReceipes();

        call.enqueue(new Callback<List<Receipe>>() {
            @Override
            public void onResponse(Call<List<Receipe>> call, Response<List<Receipe>> response) {
                if (response.body() != null) {
                    result = response.body();
                    receipesAdapter.setReceipes(result);
                    RecipeRepository.getInstance().setValue(result);
                    RecipeRepository.getInstance().storeRecipeDataInFile(getContext(), result);
                   // Log.i(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Receipe>> call, Throwable t) {
                Timber.d(t);
            }
        });

        return result;
    }

}