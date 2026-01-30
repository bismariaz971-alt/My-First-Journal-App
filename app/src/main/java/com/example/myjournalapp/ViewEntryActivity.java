package com.example.myjournalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewEntryActivity extends AppCompatActivity {
TextView tvTitle,tvContent,tvDate;
Button  btnEdit,btnDelete,btnBack;

JournalDataBase dataBase;
JournalEntry currentEntry;
int entryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tvContent);
        tvDate = findViewById(R.id.tvDate);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

        dataBase = JournalDataBase.getInstance(this);
        entryId=  getIntent().getIntExtra("ENTRY-ID",-1);
        if(entryId ==-1){
            Toast.makeText(this, "Error loading entry!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        loadEntry();

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(ViewEntryActivity.this, EditEntryActivity.class);
            intent.putExtra("ENTRY-ID", entryId);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            showDeleteConfirmation();
        });



    }
    private void loadEntry() {
        currentEntry = dataBase.jounaldao().getEntryById(entryId);
        if (currentEntry != null) {
            tvTitle.setText(currentEntry.title);
            tvDate.setText(formatDate(currentEntry.date));
            tvContent.setText(currentEntry.content);

        } else {
            Toast.makeText(this, "Entry not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    private String formatDate(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));

    }
   private void showDeleteConfirmation(){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Delete Entry");
       builder.setMessage("Are you sure you want to delete this entry?");
       builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               deleteEntry();
           }

       });
       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
       builder.show();

    }
    private void deleteEntry(){
        dataBase.jounaldao().delete(currentEntry);
        Toast.makeText(this, "Entry deleted", Toast.LENGTH_SHORT).show();
        finish();

    }
protected void onResume(){
        super.onResume();
        loadEntry();
    }
}