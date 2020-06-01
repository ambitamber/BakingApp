package com.example.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bakingapp.fragment.DetailFragment;
import com.example.bakingapp.model.Recipe;

public class DetailActivity extends AppCompatActivity {

    private static boolean isTwoPane;
    private FragmentManager fragmentManager;
    public static Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //To set the name of recipe on the appbar
        getSupportActionBar().setTitle(recipe.getName());

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, new DetailFragment())
                .commit();
    }
}
