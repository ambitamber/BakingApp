package com.example.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.bakingapp.adapter.StepAdapter;
import com.example.bakingapp.adapter.TabAdapter;
import com.example.bakingapp.fragment.DetailFragment;
import com.example.bakingapp.fragment.VideoFragment;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main_Detail extends AppCompatActivity  {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    TabAdapter tabAdapter;
    public static Recipe recipe;

    public static boolean isTwoPane;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__detail);

        setTitle(recipe.getName());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        if (findViewById(R.id.detailContainer) != null){
            isTwoPane = true;
        }else {
            isTwoPane = false;
        }

        if (isTwoPane){
            if (savedInstanceState == null){
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.container, new DetailFragment())
                        .commit();
            }
        }else{
            ButterKnife.bind(this);
            tabAdapter = new TabAdapter(getSupportFragmentManager());
            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}