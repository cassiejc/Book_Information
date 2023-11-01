package com.example.bookinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends BaseAdapter {
    private Context context;
    private List<Review> reviewList;

    public ReviewAdapter(Context context, List<String> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.review_item, viewGroup, false);
        }
        Review review = reviewList.get(i);

        TextView titleTextView = view.findViewById(R.id.tvTitle);
        TextView authorTextview = view.findViewById(R.id.tvAuthor);

        titleTextView.setText(review.getTitle());
        authorTextview.setText("Penulis: " + review.getAuthor());

        return view;
    }
}
