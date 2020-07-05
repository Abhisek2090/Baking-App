package com.example.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.utils.GsonInstance;
import com.google.gson.GsonBuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

 public class RecipeRepository {

    private static RecipeRepository sRecipeRepository;
    private List<Receipe> model;


    private RecipeRepository() {

    }

    /**
     * A method to store the preference data once it is retrieved.
     * Each model is converted to json string and then stred in a Set named dataSet.
     * @param context
     */
    public void storeRecipeDataInFile(Context context, List<Receipe> model) {

        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.RECIPE_PREFERENCE_FILE), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> dataSet = new HashSet<>();

        for (int i = 0; i < model.size(); i++) {

            Receipe recipeModel = model.get(i);
            String json = GsonInstance.getGsonInstance().toJson(recipeModel);

            dataSet.add(json);


        }

        editor.putStringSet( context.getString(R.string.recipe_ingredients), dataSet);



        editor.apply();

    }

    public String getRecipe(int modelPosition, Context context) {

        Set<String> dataSet;

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.RECIPE_PREFERENCE_FILE), Context.MODE_PRIVATE);
        dataSet = sharedPreferences.getStringSet(context.getString(R.string.recipe_ingredients), null);


        Iterator<String> iterator;
        String response = null;
        int position = 1;


        if (dataSet != null) {

            iterator = dataSet.iterator();

            while(iterator.hasNext()) {

                response = iterator.next();

                if(position == modelPosition) {

                    break;
                }

                position++;
            }

        }


        return response;

    }

    public Set<String> getRecipeSet(Context context) {


        Set<String> dataSet;

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.RECIPE_PREFERENCE_FILE), Context.MODE_PRIVATE);
        dataSet = sharedPreferences.getStringSet(context.getString(R.string.recipe_ingredients), null);

        return dataSet;

    }


    /**
     * This methods makes the RecipeRepository class a singleton
     * @RecipeRepository
     */
    public static RecipeRepository getInstance() {

        if( sRecipeRepository == null ) {
            sRecipeRepository = new RecipeRepository();
        }

        return sRecipeRepository;
    }


    public void setValue(List<Receipe> value) {
        this.model = value;
    }
}
