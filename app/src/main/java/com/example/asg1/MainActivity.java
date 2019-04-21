package com.example.asg1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.asg1.food.name.MESSAGE";
    private String sharedPreferencesFile = "com.example.asg1sharedprefs";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView rating1 = findViewById(R.id.rating1);
        TextView rating2 = findViewById(R.id.rating2);
        TextView rating3 = findViewById(R.id.rating3);
        TextView rating4 = findViewById(R.id.rating4);
        float rating1F, rating2F, rating3F, rating4F;
        String ratingPrefix = getString(R.string.rating);
        /*if (savedInstanceState != null) {
            rating1F = savedInstanceState.getFloat("rating_" + getString(R.string.food1), 0.0f);
            rating2F = savedInstanceState.getFloat("rating_" + getString(R.string.food2), 0.0f);
            rating3F = savedInstanceState.getFloat("rating_" + getString(R.string.food3), 0.0f);
            rating4F = savedInstanceState.getFloat("rating_" + getString(R.string.food4), 0.0f);
        } else*/
        sharedPreferences = getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE);
        rating1F = sharedPreferences.getFloat("rating_" + getString(R.string.food1), 0.0f);
        rating2F = sharedPreferences.getFloat("rating_" + getString(R.string.food2), 0.0f);
        rating3F = sharedPreferences.getFloat("rating_" + getString(R.string.food3), 0.0f);
        rating4F = sharedPreferences.getFloat("rating_" + getString(R.string.food4), 0.0f);

        rating1.setText(ratingPrefix + rating1F);
        rating2.setText(ratingPrefix + rating2F);
        rating3.setText(ratingPrefix + rating3F);
        rating4.setText(ratingPrefix + rating4F);
    }

    public void showDetails1(View view) {
        showDetails(view, R.string.food1);
    }

    public void showDetails2(View view) {
        showDetails(view, R.string.food2);
    }

    public void showDetails3(View view) {
        showDetails(view, R.string.food3);
    }

    public void showDetails4(View view) {
        showDetails(view, R.string.food4);
    }

    public void showDetails(View view, int foodName) {
        Intent intent = new Intent(MainActivity.this, DetailedViewActivity.class);
        intent.putExtra(EXTRA_MESSAGE, getString(foodName));
        startActivity(intent);
    }
}
