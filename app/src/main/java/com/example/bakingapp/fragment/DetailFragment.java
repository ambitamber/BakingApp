package com.example.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapter.IngredientAdapter;
import com.example.bakingapp.adapter.StepAdapter;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements IngredientAdapter.IngredientAdapterOnClickHandler,StepAdapter.StepAdapterOnClickHandler{

    IngredientAdapter ingredientAdapter;
    StepAdapter stepAdapter;

    public static Recipe recipe;
    private List<Steps> stepsList;
    private List<Ingredients> ingredientsList;

    @BindView(R.id.RV_ingredients)
    RecyclerView recyclerView_ingredients;
    @BindView(R.id.RV_steps)
    RecyclerView recyclerView_steps;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        ButterKnife.bind(rootView);

        Context context = getContext();

        stepsList = new ArrayList<>();
        stepsList = recipe.getSteps();

        ingredientsList = new ArrayList<>();
        ingredientsList = recipe.getIngredients();

        ingredientAdapter = new IngredientAdapter(ingredientsList,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_ingredients.setLayoutManager(layoutManager);
        recyclerView_ingredients.setAdapter(ingredientAdapter);

        stepAdapter = new StepAdapter(stepsList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_steps.setLayoutManager(linearLayoutManager);
        recyclerView_steps.setAdapter(stepAdapter);

        return rootView;
    }

    @Override
    public void onClick(int index) {

    }
}
