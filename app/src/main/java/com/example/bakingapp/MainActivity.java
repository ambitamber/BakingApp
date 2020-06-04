package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.adapter.RecipeAdapter;
import com.example.bakingapp.fragment.DetailFragment;
import com.example.bakingapp.fragment.IngredientFragment;
import com.example.bakingapp.fragment.StepsFragment;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.RecipeClient;
import com.example.bakingapp.utils.RecipeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecipeAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView errorTV;
    private ProgressBar progressBar;
    private ArrayList<Recipe> mRecipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        errorTV = findViewById(R.id.TV_error);
        progressBar = findViewById(R.id.progress_circular);

        RecipeService recipeService = RecipeClient.getRetrofit().create(RecipeService.class);
        Call<ArrayList<Recipe>> call = recipeService.getAllRecipes();
        call.enqueue(
                new Callback<ArrayList<Recipe>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                        mRecipeData = response.body();
                        createList(mRecipeData);
                        showData();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, t.toString());
                        showError();
                    }
                }
        );

    }

    private void createList(List<Recipe> recipeList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,screenSize());
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new RecipeAdapter(recipeList, this, this);
        recyclerView.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(int index) {
        Context context = this;
        Recipe recipeItem = mRecipeData.get(index);
        Main_Detail.recipe = recipeItem;
        IngredientFragment.recipe = recipeItem;
        StepsFragment.recipe = recipeItem;
        DetailFragment.recipe = recipeItem;
        Intent intent = new Intent(context, Main_Detail.class);
        startActivity(intent);
    }

    private void showError() {
        progressBar.setVisibility(View.INVISIBLE);
        errorTV.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void showData() {
        errorTV.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private int screenSize() {
        Display display = this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int size = Math.round(dpWidth/450);
        Log.e("MainActivity", "Screen Size: "+ size);
        return size;
    }

}
