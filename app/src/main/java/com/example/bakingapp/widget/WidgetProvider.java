package com.example.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.bakingapp.R;

public class WidgetProvider extends AppWidgetProvider {

    static void UpdateWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        CharSequence widgetTitle = sharedPreferences.getString(context.getResources().getString(R.string.title), "TITLE");

        CharSequence widgetText = sharedPreferences.getString(context.getResources().getString(R.string.ingredients), "INGREDIENTS");

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.appwidget_title, widgetTitle);

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
