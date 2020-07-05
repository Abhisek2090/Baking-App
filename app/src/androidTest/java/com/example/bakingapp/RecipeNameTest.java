package com.example.bakingapp;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.bakingapp.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class RecipeNameTest {

    @Rule public ActivityTestRule<MainActivity> recipeDetailActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mRecipeIdlingResource;

    private String mRecipeName = "Nutella Pie";

    @Before
    public void registerIdlingResource() {
        mRecipeIdlingResource = recipeDetailActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mRecipeIdlingResource);
    }

    @Test
    public void checkForName_afterRecyclerViewClick() {

        onView(withId(R.id.receipesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.receipeNameTV)).check(matches(withText(mRecipeName)));


    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(mRecipeIdlingResource);
    }

}
