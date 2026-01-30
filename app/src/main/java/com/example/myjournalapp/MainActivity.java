package com.example.myjournalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private FloatingActionButton floatingActionButton;
private List<JournalEntry> journalList ;
private JournalRecyclerAdapter adapter;
private JournalDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataBase = JournalDataBase.getInstance(this);

        loadAllEntriesFromDataBase();

        adapter = new JournalRecyclerAdapter(journalList, new JournalRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onClick(JournalEntry entry) {
                Intent intent = new Intent(MainActivity.this, ViewEntryActivity.class);
                intent.putExtra("ENTRY-ID", entry.id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WriteEntryActivity.class);
            startActivity(intent);

        });

    }
    public  void loadAllEntriesFromDataBase(){
        journalList = dataBase.jounaldao().getAllEntries();
        if(journalList.isEmpty()){
            Toast.makeText(this, "No Entries yet! Click + to add one", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume(){

        super.onResume();
        loadAllEntriesFromDataBase();
        adapter = new JournalRecyclerAdapter(journalList, new JournalRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onClick(JournalEntry entry) {
                Intent intent = new Intent(MainActivity.this, ViewEntryActivity.class);
                intent.putExtra("ENTRY-ID",entry.id);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);
    }
}