package com.example.bakingapp.utils;

import android.text.TextUtils;

import com.example.bakingapp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<Recipe> getRecipeFromJson(String JsonStr) throws JSONException {
        if (TextUtils.isEmpty(JsonStr)){
            return null;
        }

        List<Recipe> recipeData = new ArrayList<>();
        Recipe recipe;
        JSONArray jsonArray = new JSONArray(JsonStr);
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.optString("title");
            String servings = jsonObject.optString("servings");
            recipe = new Recipe(title,servings);
            recipeData.add(recipe);
        }
        return recipeData;
    }
}
