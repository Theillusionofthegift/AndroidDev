package com.example.androiddev.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androiddev.entity.Settings;

@Dao
public interface SettingDao {

    @Query("SELECT * FROM settings" )
    LiveData<Settings> getSettings();

    @Update
    void updateSettings(Settings... settings);

}
