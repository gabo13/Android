package com.gabisoft.darabszam_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class DarabszamAppWidget extends AppWidgetProvider {
    static  void  updateAppWidget(Context context,
                                  AppWidgetManager appWidgetManager,
                                  int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

        Darabszam dsz = new Darabszam();
        views.setTextViewText(R.id.time_act,"Time: "+dsz.getTime());
        views.setTextViewText(R.id.darab, "Darab: "+dsz.getDarab());

        Intent intentUpdate = new Intent(context, DarabszamAppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray );

        PendingIntent pendingUpdate = PendingIntent.getBroadcast( context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.update,pendingUpdate);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context, "Widget updated!", Toast.LENGTH_SHORT).show();
        }
    }
}
