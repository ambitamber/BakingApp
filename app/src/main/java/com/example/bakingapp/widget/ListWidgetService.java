package com.example.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;

import java.util.List;

public class ListWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}
 class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context context;
    private List<Ingredients> ingredients;

    public ListRemoteViewsFactory(Context applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public void onCreate() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "baking_preferences",
                0
        );

        String serializedRecipe = sharedPreferences.getString(
                "serialized_recipe",
                null);

        sharedPreferences.edit().clear().apply();

        if (TextUtils.isEmpty(serializedRecipe)) {
            ingredients = null;
            return;
        }

        Recipe recipe = Recipe.fromJson(serializedRecipe);
        ingredients = recipe.getIngredients();
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredients == null){
            return 0;
        }
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (ingredients == null || ingredients.size() == 0){
            return null;
        }
        Ingredients ingredient = ingredients.get(position);
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_list_item);
        String quantityMeasurement = ingredient.getQuantity() + " " + ingredient.getMeasure().toLowerCase();
        views.setTextViewText(R.id.widget_text_view_ingredient_quantity_measurement, quantityMeasurement);
        views.setTextViewText(R.id.widget_text_view_ingredient_name, ingredient.getIngredient());
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
