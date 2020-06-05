package com.example.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

public class DetailFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler,IngredientAdapter.IngredientAdapterOnClickHandler{

    StepAdapter stepAdapter;
    IngredientAdapter ingredientAdapter;

    public static Recipe recipe;
    public static List<Steps> stepList = new ArrayList<>();
    private List<Ingredients> ingredientList = new ArrayList<>();

    @BindView(R.id.detail_step_Fragment)
    RecyclerView mRecyclerView;
    @BindView(R.id.detail_ingredient_Fragment)
    RecyclerView ingredientRecyclerView;

    public DetailFragment(){
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail,container,false);
        ButterKnife.bind(this,view);

        stepList = recipe.getSteps();
        stepAdapter = new StepAdapter(stepList,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(stepAdapter);

        ingredientList = recipe.getIngredients();
        ingredientAdapter = new IngredientAdapter(ingredientList,this);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        return view;
    }

    @Override
    public void onClick(int index) {
            VideoFragment.steps = stepList.get(index);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.detailContainer,new VideoFragment()).commit();
    }
}
