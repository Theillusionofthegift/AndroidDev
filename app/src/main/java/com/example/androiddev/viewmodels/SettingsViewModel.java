package com.example.androiddev.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androiddev.AppDatabase;
import com.example.androiddev.AppDatabaseSingleton;
import com.example.androiddev.entity.Settings;


public class SettingsViewModel extends ViewModel {

    public LiveData<Settings> loadSettings(Context context) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.settingsDao().getSettings();
    }

    public void updateSettings(Context context, Settings... settings) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().updateSettings(settings);
        });
    }

}
