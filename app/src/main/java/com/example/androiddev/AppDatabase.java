package com.example.androiddev;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androiddev.dao.SettingDao;
import com.example.androiddev.entity.Settings;

@Database(entities = {Settings.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingDao settingsDao();
}
