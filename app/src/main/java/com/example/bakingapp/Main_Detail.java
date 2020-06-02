package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bakingapp.adapter.TabAdapter;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main_Detail extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    TabAdapter tabAdapter;
    public static Recipe recipe;
    private List<Steps> stepsList;
    private List<Ingredients> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__detail);
        ButterKnife.bind(this);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setTitle(recipe.getName());

        stepsList = new ArrayList<>();
        stepsList = recipe.getSteps();
        ingredientsList = new ArrayList<>();
        ingredientsList = recipe.getIngredients();

    }
}