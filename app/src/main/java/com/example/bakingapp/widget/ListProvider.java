package com.example.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeRepository;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Receipe;
import com.example.bakingapp.utils.GsonInstance;


import java.util.ArrayList;

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<String> listItemList;
    private Context mContext = null;
    private int mRecipeNumber;
    private RecipeRepository recipeRepository;
    private Receipe mRecipeModel;


    public ListProvider(Context context, Intent intent) {
        if(intent.hasExtra("RECIPE NUMBER")){
            mRecipeNumber = intent.getIntExtra("RECIPE NUMBER", 0);
        }
        mContext = context;
    }



    @Override
    public void onCreate() {

        listItemList = new ArrayList<>();

        mRecipeModel = GsonInstance.getGsonInstance()
                .fromJson(RecipeRepository.getInstance()
                        .getRecipe(mRecipeNumber, mContext), Receipe.class);

        setup();


    }

    @Override
    public void onDataSetChanged() {

    //    setup();

    }

    @Override
    public void onDestroy() {
        listItemList.clear();
    }

    @Override
    public int getCount() {
        if(listItemList == null)
            return 0;
        return listItemList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        remoteViews.setTextViewText(R.id.widget_single_text, listItemList.get(i));
        return  remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void setup() {



        for(Ingredients ingredient : mRecipeModel.getIngredients()) {

            StringBuilder buffer = new StringBuilder();

            buffer.append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getMeasure())
                    .append(" of ")
                    .append(ingredient.getIngredient())
                    .append("\n");

            listItemList.add(new String(buffer));

        }

    }

}
