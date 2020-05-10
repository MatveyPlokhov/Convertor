package com.mapl.converter.model.database;

import android.app.Application;

import androidx.room.Room;

import com.mapl.converter.model.database.dao.AdvicesDao;

public class AdviceSingleton extends Application {
    private static AdviceSingleton instance;
    private AdvicesDatabase database;

    public static AdviceSingleton getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AdvicesDatabase.class,
                "current_weather").build();
    }

    public AdvicesDao getAdvicesDao() {
        return database.getAdvisesDao();
    }
}
