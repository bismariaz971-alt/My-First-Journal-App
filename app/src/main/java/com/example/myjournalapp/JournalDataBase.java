package com.example.myjournalapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kotlin.jvm.Synchronized;

@Database(entities =JournalEntry.class,version = 1)
public  abstract class JournalDataBase extends RoomDatabase {
    public  abstract JournalDao jounaldao();

    public static JournalDataBase instance;

    public static synchronized JournalDataBase getInstance(Context context){
        if(instance == null ){
            instance = Room.databaseBuilder
                    (context.getApplicationContext(),
                            JournalDataBase.class,
                            "journalDataBase"
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
