package com.example.myjournalapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JournalEntry")
public class JournalEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String content;
    public long date;
    public JournalEntry(String title,String content, long date){
        this.title = title;
        this.content = content;
        this.date = date;
    }
    public String getTitle(){
        return title;
    }
    public long getDate(){
        return date;
    }
    public String getContent(){
        return content;
    }


}
