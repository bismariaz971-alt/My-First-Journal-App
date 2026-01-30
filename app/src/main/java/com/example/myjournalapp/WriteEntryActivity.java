package com.example.myjournalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WriteEntryActivity extends AppCompatActivity {
TextView txtTitle,txtContent;
EditText edtTitle,edtContent;

Button btnSave, btnCancel;

JournalDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_entry);

        txtTitle = findViewById(R.id.labelTitleWriteScreen);
        txtContent = findViewById(R.id.labelContentWriteScreen);
        edtTitle = findViewById(R.id.et_Title);
        edtContent =findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btn_Save);
        btnCancel = findViewById(R.id.btn_Cancel);

        dataBase = JournalDataBase.getInstance(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntry();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void saveEntry(){
        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();
        if(title.isEmpty()){
            Toast.makeText(this, "Please Enter title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (content.isEmpty()){
            Toast.makeText(this, "Please Enter Content", Toast.LENGTH_SHORT).show();
            return;
        }

        JournalEntry newEntry = new JournalEntry(title,content,System.currentTimeMillis());
        dataBase.jounaldao().insert(newEntry);

        Toast.makeText(this, "Entry Saved Successfuly", Toast.LENGTH_SHORT).show();
        finish();

    }
}