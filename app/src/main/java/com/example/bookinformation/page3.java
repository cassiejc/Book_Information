package com.example.bookinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class page3 extends AppCompatActivity {
    private Button button_save, button_cancel;
    private EditText editText_author, editText_Review, editText_title;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        dbHelper = new DatabaseHelper(this);

        button_cancel = findViewById(R.id.buttoncancel);
        button_save = findViewById(R.id.buttonsave);
        editText_author = findViewById(R.id.editText_author);
        editText_title = findViewById(R.id.editText_title);
        editText_Review = findViewById(R.id.editText_review);

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(page3.this, page2.class);
                startActivity(i);
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String author = editText_author.getText().toString();
                String title = editText_title.getText().toString();
                String review = editText_Review.getText().toString();

                saveDataToDatabase(author, title, review);

                Intent i = new Intent(page3.this, page2.class);
                startActivity();
            }
        });
    }

    private void saveDataToDatabase(String author, String title, String review){
        dbHelper.insertData(author, title, review);
    }
}