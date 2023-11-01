package com.example.bookinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class page2 extends AppCompatActivity {
    private Button button_add;
    private ListView reviewListview;
    private String username;
    private List<String> reviewList;
    private ReviewAdapter reviewAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        username = getIntent().getStringExtra("username");
        button_add = findViewById(R.id.button_Add);
        reviewList = new ArrayList<String>();

        Cursor c = dbHelper.readAllData();

        if (c != null && c.moveToFirst()) {
            int titleIndex = c.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
            int authorIndex = c.getColumnIndex(DatabaseHelper.COLUMN_AUTHOR);
            int reviewIndex = c.getColumnIndex(DatabaseHelper.COLUMN_REVIEW);
            do {
                String title = c.getString(titleIndex);
                String author = c.getString(authorIndex);
                String reviewText = c.getString(reviewIndex);
                String review = String.valueOf(new Review(username, title, author, reviewText));
                reviewList.add(review);
            } while (c.moveToNext());
            reviewAdapter = new ReviewAdapter(this, reviewList);
            reviewListview.setAdapter(reviewAdapter);

            c.close();
        }

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(page2.this, page3.class);
                startActivity(i);
            }
        });
    }
}