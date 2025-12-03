package com.example.gymlogapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gymlogapp.Database.entities.GymLog;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GymLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // resolves the conflict to replace old w new
    void insert(GymLog gymlog);

    @Query("Select * from " + GymLogDatabase.GYM_LOG_TABLE)
    ArrayList<GymLog> getAllRecords();

}
