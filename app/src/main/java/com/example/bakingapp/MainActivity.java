package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bakingapp.adapter.RecipeAdapter;
import com.example.bakingapp.model.Recipe;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    private RecipeAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView errorTV;
    private ProgressBar progressBar;
    private ArrayList<Recipe> mRecipeData;
    GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new RecipeAdapter(mRecipeData,this,this);
        recyclerView = findViewById(R.id.recyclerView);
        errorTV = findViewById(R.id.TV_error);
        progressBar = findViewById(R.id.progress_circular);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onClick(Recipe recipe) {
        String title = recipe.getTitle();
        String servings = recipe.getServings();
    }

    private void showError(){
        progressBar.setVisibility(View.INVISIBLE);
        errorTV.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void showData(){
        errorTV.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public class FetchDataTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
