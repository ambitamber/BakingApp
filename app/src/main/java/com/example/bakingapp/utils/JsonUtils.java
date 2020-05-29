package com.example.bakingapp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String TAG = JsonUtils.class.getSimpleName();

    public static List<Recipe> getRecipeFromJson(String JsonStr) throws JSONException {
        if (TextUtils.isEmpty(JsonStr)){
            return null;
        }

        List<Recipe> recipeData = new ArrayList<>();
        Recipe recipe;
        JSONArray jsonArray = new JSONArray(JsonStr);
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.optString("name");
            Log.i(TAG,"Name: "+ title);
            String servings = jsonObject.optString("servings");
            JSONArray steps = jsonObject.getJSONArray("steps");
            String stepslenght = String.valueOf(steps.length());
            recipe = new Recipe(title,servings,stepslenght);
            recipeData.add(recipe);
        }
        return recipeData;
    }

    public static List<Steps> getStepsFromJson(String stepsJson) throws JSONException {
        if (TextUtils.isEmpty(stepsJson)){
            return null;
        }

        List<Steps> stepsData = new ArrayList<>();
        Steps steps;
        JSONObject jsonObject = new JSONObject(stepsJson);
        JSONArray jsonArray = jsonObject.getJSONArray("steps");
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject currentstep = jsonArray.getJSONObject(i);
            String shortDescription = currentstep.optString("shortDescription");
            String description = currentstep.optString("description");
            String videoURL = currentstep.optString("videoURL");
            String thumbnailURL = currentstep.optString("thumbnailURL");
            steps = new Steps(shortDescription,description,videoURL,thumbnailURL);
            stepsData.add(steps);
        }
        return stepsData;
    }
}
