package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.adapter.RecipeAdapter;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.JsonUtils;
import com.example.bakingapp.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    String siteUrl = "https://d17h27t6h515a5.cloudfront.net";
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

        URL url = NetworkUtils.builtUrl(siteUrl);
        new FetchDataTask().execute(url);
    }

    @Override
    public void onClick(Recipe recipe) {
        String title = recipe.getTitle();
        String servings = recipe.getServings();
        Toast.makeText(MainActivity.this,title +" - "+servings,Toast.LENGTH_SHORT).show();
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
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {

            if (urls.length == 0){
                return null;
            }
            URL query = urls[0];
            String searchResults  = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(query);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String recipes) {
            progressBar.setVisibility(View.INVISIBLE);
            if (recipes != null && !recipes.equals("")){
                showData();
                try {
                    mRecipeData = (ArrayList<Recipe>) JsonUtils.getRecipeFromJson(recipes);
                    mAdapter.setRecipeList(mRecipeData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                showError();
            }

        }
    }
}
