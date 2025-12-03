package com.example.gymlogapp.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import com.example.gymlogapp.database.entities.GymLog;


@Dao
public interface GymLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // resolves the conflict to replace old w new
    void insert(GymLog gymlog);

    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " ORDER BY date DESC")
    List<GymLog> getAllRecords();

}