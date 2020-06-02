package com.example.bakingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapter.IngredientAdapter;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientFragment extends Fragment implements IngredientAdapter.IngredientAdapterOnClickHandler {

    public static Recipe recipe;
    private List<Ingredients> ingredientsList;
    @BindView(R.id.fragment_ingredient_rv)
    RecyclerView recyclerView;
    IngredientAdapter ingredientAdapter;

    public IngredientFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        ButterKnife.bind(this, view);

        ingredientsList = new ArrayList<>();
        ingredientsList = recipe.getIngredients();
        ingredientAdapter = new IngredientAdapter(ingredientsList, IngredientFragment.this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(ingredientAdapter);
        return view;
    }

    @Override
    public void onClick(int index) {

    }
}
