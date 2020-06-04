package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bakingapp.adapter.TabAdapter;
import com.example.bakingapp.fragment.DetailFragment;
import com.example.bakingapp.fragment.VideoFragment;
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

    public static boolean isTwoPane;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__detail);


        setTitle(recipe.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stepsList = new ArrayList<>();
        stepsList = recipe.getSteps();
        ingredientsList = new ArrayList<>();
        ingredientsList = recipe.getIngredients();

        if (findViewById(R.id.detailContainer) != null){
            isTwoPane = true;
        }else {
            isTwoPane = false;
        }
        if (isTwoPane){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }else{
            ButterKnife.bind(this);
            tabAdapter = new TabAdapter(getSupportFragmentManager());
            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}