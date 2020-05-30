package com.example.bakingapp.utils;

import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Recipe>> getAllRecipes();
}
