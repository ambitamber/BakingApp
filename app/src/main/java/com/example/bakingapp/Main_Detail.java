package com.example.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.bakingapp.adapter.TabAdapter;
import com.example.bakingapp.fragment.TabletDetailFragment;
import com.example.bakingapp.fragment.TabletVideoFragment;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.widget.Widget;
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
    public static final String ARG_RECIPE = "recipe_key";
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
                        .add(R.id.container, new TabletDetailFragment())
                        .commit();
                fragmentManager.beginTransaction()
                        .add(R.id.detailContainer,new TabletVideoFragment())
                        .commit();
            }
        }else{
            ButterKnife.bind(this);
            tabAdapter = new TabAdapter(getSupportFragmentManager());
            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        updateBakingWidget(recipe,this);
    }

    private void updateBakingWidget(Recipe recipe, Context context) {
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = widgetManager.getAppWidgetIds(new ComponentName(this, Widget.class));
        Widget.updateBakingWidgets(context, widgetManager, appWidgetIds, recipe);
    }
}