package com.example.bakingapp.fragment;

import android.content.Intent;
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
import com.example.bakingapp.VideoActivity;
import com.example.bakingapp.adapter.StepAdapter;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler{
    public static Recipe recipe;
    @BindView(R.id.fragment_step_rv)
    RecyclerView recyclerView;
    private List<Steps> stepsList;
    private StepAdapter stepAdapter;

    public StepsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step,container,false);
        ButterKnife.bind(this,view);

        stepsList = new ArrayList<>();
        stepsList = recipe.getSteps();
        stepAdapter = new StepAdapter(stepsList,StepsFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(stepAdapter);

        return view;
    }

    @Override
    public void onClick(int index) {
        VideoActivity.steps = stepsList.get(index);
        Intent intent = new Intent(getActivity(),VideoActivity.class);
        startActivity(intent);

    }
}
