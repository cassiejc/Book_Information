package com.example.bookinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button_begin;
    private EditText editText_username;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        dbHelper = new DatabaseHelper(this);

        button_begin=findViewById(R.id.button_begin);
        editText_username = findViewById(R.id.editText_username);

        button_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText_username.getText().toString();
                saveUsername(username);

                Intent i = new Intent(MainActivity.this, page2.class);
                startActivity(i);
            }
        });
    }

    private void saveUsername(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        db.insert(DatabaseHelper.TABLE_USER, null, values);
        db.close();
    }
}