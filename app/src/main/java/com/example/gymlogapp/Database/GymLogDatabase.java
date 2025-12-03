package com.example.gymlogapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gymlogapp.Database.entities.GymLog;

@Database(entities = {GymLog.class}, version = 1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {
    public static final String gymLogTable = "gymLogTable";
}
