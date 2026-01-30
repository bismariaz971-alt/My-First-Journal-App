package com.example.myjournalapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditEntryActivity extends AppCompatActivity {
EditText edtTitle,edtContent;
TextView tvTitle,tvContent;
Button btnUpdate,btnCancel;

JournalDataBase dataBase;
JournalEntry currentEntry;
int entryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry2);

        edtTitle= findViewById(R.id.etTitle);
        edtContent = findViewById(R.id.etContent);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        dataBase = JournalDataBase.getInstance(this);
        entryId = getIntent().getIntExtra("ENTRY-ID",-1);
        if(entryId== -1){
            Toast.makeText(this, "Error Loading Entry!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        loadEntry();
       btnUpdate.setOnClickListener(v -> {
           updateEntry();
       });

       btnCancel.setOnClickListener(v -> {
           finish();
       });


    }
    private void loadEntry(){
        currentEntry = dataBase.jounaldao().getEntryById(entryId);
        if(currentEntry!=null){
            edtTitle.setText(currentEntry.title);
            edtContent.setText(currentEntry.content);

        }
        else{
            Toast.makeText(this, "Entry not found!", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
    private void updateEntry(){
        String newTitle = edtTitle.getText().toString().trim();
        String newContent = edtContent.getText().toString().trim();

        if(newTitle.isEmpty()){
            Toast.makeText(this, "Please Enter tilte!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(newContent.isEmpty()){
            Toast.makeText(this, "Please Enter some content!", Toast.LENGTH_SHORT).show();
            return;
        }
        currentEntry.title = newTitle;
        currentEntry.content = newContent;

        dataBase.jounaldao().update(currentEntry);

        Toast.makeText(this, "Entry Updated!", Toast.LENGTH_SHORT).show();
        finish();
    }
}