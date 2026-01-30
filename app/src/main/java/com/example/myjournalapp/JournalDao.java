package com.example.myjournalapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JournalDao {
    @Insert
    void insert(JournalEntry entry);

    @Update
    void update(JournalEntry entry);

    @Delete
    void delete(JournalEntry entry);

    @Query("SELECT * FROM JournalEntry ORDER BY date DESC")
    List<JournalEntry> getAllEntries();
    @Query("SELECT * FROM JournalEntry WHERE id =:entryID")
    JournalEntry getEntryById(int entryID);
}
