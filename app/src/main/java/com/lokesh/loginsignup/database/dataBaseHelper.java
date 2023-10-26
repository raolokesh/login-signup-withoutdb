package com.lokesh.loginsignup.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = userEntity.class,exportSchema = false,version = 1)
public abstract class dataBaseHelper extends RoomDatabase {


    private static final String DB_NAME = "userdata";
    private static dataBaseHelper instance;


    public static synchronized dataBaseHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,dataBaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
    public abstract userEntityDao userEntityDao();
}
