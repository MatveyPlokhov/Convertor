package com.mapl.converter.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mapl.converter.model.database.dao.AdvicesDao;
import com.mapl.converter.model.database.model.Advice;

@Database(entities = {Advice.class}, version = 1, exportSchema = false)
public abstract class AdvicesDatabase extends RoomDatabase {
    public abstract AdvicesDao getAdvisesDao();
}
