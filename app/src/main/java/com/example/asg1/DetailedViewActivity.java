package com.example.asg1;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.widget.*;
import android.view.*;

public class DetailedViewActivity extends AppCompatActivity {
    private String foodName;
    private float originalRating;
    private String sharedPreferencesFile = "com.example.asg1sharedprefs";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        foodName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView foodNameView = findViewById(R.id.detailed_food_name);
        foodNameView.setText(foodName);
        ImageView imageView = findViewById(R.id.detailedImageView);
        imageView.setImageDrawable(getDrawable(getPictureID(foodName)));
        TextView restaurantView = findViewById(R.id.detailed_restaurant);
        String restaurant = getRestaurantByFoodName(foodName);
        restaurantView.setText(restaurant);
        TextView addressView = findViewById(R.id.detailed_address);
        addressView.setText(getRestaurantAddress(restaurant));
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        sharedPreferences = getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE);
        this.originalRating = sharedPreferences.getFloat("rating_" + foodName, 0.0f);
        ratingBar.setRating(this.originalRating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                LinearLayout layout = findViewById(R.id.detailed_bottom);
                layout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            goBack();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    int getPictureID(String foodName) {
        if (foodName.equals(getString(R.string.food1))) {
            return R.drawable.kebab_tn_rect;
        } else if (foodName.equals(getString(R.string.food2))) {
            return R.drawable.pizza_tn_rect;
        } else if (foodName.equals(getString(R.string.food3))) {
            return R.drawable.vegan_pork_tn_rect;
        } else {
            return R.drawable.vegan_fish_tn_rect;
        }
    }

    String getRestaurantByFoodName(String name) {
        if (name.equals(getString(R.string.food1)) || name.equals(getString(R.string.food2))) {
            return getString(R.string.ebeneezer_longer);
        } else {
            return getString(R.string.vegether_longer);
        }
    }

    String getRestaurantAddress(String restaurant) {
        if (restaurant.equals(getString(R.string.ebeneezer_longer))) {
            return getString(R.string.ebeneezer_address);
        } else {
            return getString(R.string.vegether_address);
        }
    }

    public void onSavePressed(View view) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        //state.putFloat("rating_"+foodName,ratingBar.getRating());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating_" + foodName, ratingBar.getRating());
        editor.apply();
        goBack();
    }

    public void onCancelPressed(View view) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(originalRating);
        LinearLayout layout = findViewById(R.id.detailed_bottom);
        layout.setVisibility(View.INVISIBLE);
    }

    void goBack() {
        startActivity(new Intent(DetailedViewActivity.this, MainActivity.class));
    }
}
