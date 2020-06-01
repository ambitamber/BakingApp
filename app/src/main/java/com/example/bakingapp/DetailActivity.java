package com.example.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.adapter.IngredientAdapter;
import com.example.bakingapp.adapter.StepAdapter;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler,IngredientAdapter.IngredientAdapterOnClickHandler{

    public static Recipe recipe;
    private List<Steps> stepsList;
    private List<Ingredients> ingredientsList;
    @BindView(R.id.RV_steps)
    RecyclerView stepsRecyclerView;
    @BindView(R.id.RV_ingredients)
    RecyclerView ingredientsRecyclerView;
    private IngredientAdapter ingredientAdapter;
    private StepAdapter stepAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setTitle(recipe.getName());


        stepsList = new ArrayList<>();
        stepsList = recipe.getSteps();
        stepAdapter = new StepAdapter(stepsList,this);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stepsRecyclerView.setAdapter(stepAdapter);

        ingredientsList = new ArrayList<>();
        ingredientsList = recipe.getIngredients();
        ingredientAdapter = new IngredientAdapter(ingredientsList,this);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

    }

    @Override
    public void onClick(int index) {

    }
}
