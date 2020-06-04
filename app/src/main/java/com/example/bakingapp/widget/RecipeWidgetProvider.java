package com.example.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.example.bakingapp.Main_Detail;
import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;

public class RecipeWidgetProvider extends AppWidgetProvider {

    private static final String AUTHORITY = "com.example.bakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static void UpdateWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId){

               // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        SharedPreferences sharedPreferences = context.getSharedPreferences("baking_preferences", 0);

        String serializedRecipe = sharedPreferences.getString("serialized_recipe", null);

        if (!TextUtils.isEmpty(serializedRecipe)) {
            Recipe recipe = Recipe.fromJson(serializedRecipe);
            views.setTextViewText(R.id.widget_text_view_recipe_name, recipe.getName());
            Intent appIntent = new Intent(context, Main_Detail.class);
            appIntent.putExtra("recipe", recipe);
            PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_layout, appPendingIntent);
        }

        Intent intent = new Intent(context, ListWidgetService.class);
        Uri uri = BASE_CONTENT_URI
                .buildUpon()
                .appendPath("widget")
                .appendPath("id")
                .appendPath(String.valueOf(appWidgetId))
                .build();
        intent.setData(uri);
        views.setRemoteAdapter(R.id.widget_list_view, intent);
        views.setEmptyView(R.id.widget_empty_view, R.id.empty_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetID : appWidgetIds){
            UpdateWidget(context, appWidgetManager, appWidgetID);
        }
    }
}
