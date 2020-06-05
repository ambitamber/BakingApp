package com.example.bakingapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeListText {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void recyclerViewOnClick(){
        onView(withId(R.id.recyclerView)).perform(click());
    }
    @Test

    public void recyclerViewNotEmpty(){
        onView(withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(1)));
    }


}
