package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bakingapp.adapter.RecipeAdapter;
import com.example.bakingapp.adapter.StepAdapter;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements  StepAdapter.StepAdapterOnClickHandler {

    private ProgressBar progressBar;
    private StepAdapter stepAdapter;
    private Recipe recipe;
    private List<Steps> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null){
            giveError("There is no data");
        }
        assert intent != null;
        recipe = (Recipe) intent.getSerializableExtra("recipe");
        if (recipe == null){
            giveError("No Recipe Data");
        }

        progressBar = findViewById(R.id.progress_circular_detail);
        stepAdapter = new StepAdapter(this,steps,this);

    }

    private void giveError(String meg) {
        finish();
        Toast.makeText(this, meg, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(Steps steps) {

    }

    public class FetchStepTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(URL... urls) {
            return null;
        }
    }
}
