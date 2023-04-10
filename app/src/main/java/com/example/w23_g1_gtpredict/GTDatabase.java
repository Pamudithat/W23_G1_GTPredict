package com.example.w23_g1_gtpredict;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GTData.class}, version = 1)
public abstract class GTDatabase extends RoomDatabase {
    public abstract GTDataDao gtDataDao();
}

