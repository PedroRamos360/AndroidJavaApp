package com.example.mobileapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GestureDetector gestureDetector;
    RelativeLayout imageContainer;
    RelativeLayout backgroundLikeFeedback;
    List<Integer> peopleImages;
    ImageView mainImage;
    TextView likeFeedbackText;
    private int currentIndex = 0;
    private int swipeIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.getMenu().findItem(R.id.navigation_search).setChecked(true);
        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.navigation_profile) {
                    Intent intent = new Intent(MainActivity.this, Messages.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        hideSystemUI();
        loadPeopleImages();
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        imageContainer = findViewById(R.id.imageContainer);
        mainImage = findViewById(R.id.mainImage);
        backgroundLikeFeedback = findViewById(R.id.backgroundLikeFeedback);
        likeFeedbackText = findViewById(R.id.likeFeedbackText);
        mainImage.setImageResource(peopleImages.get(currentIndex));
    }

    private void loadPeopleImages() {
       peopleImages = new ArrayList<>();
       peopleImages.add(R.mipmap.woman1);
       peopleImages.add(R.mipmap.woman2);
       peopleImages.add(R.mipmap.woman3);
       peopleImages.add(R.mipmap.woman4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void animateSwipe(final View view, float translateX) {
        view.animate()
                .translationXBy(translateX)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.println("Executed" + swipeIndex);
                        if (swipeIndex % 2 == 0) {
                            nextImage();
                            view.animate()
                                    .translationX(0)
                                    .start();
                        }
                        swipeIndex++;
                    }
                })
                .start();
    }

    private static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void nextImage() {
        currentIndex++;
        if (currentIndex > peopleImages.size() - 1) {
            imageContainer.setVisibility(View.GONE);
            TextView warnText = findViewById(R.id.noPeopleWarn);
            warnText.setVisibility(View.VISIBLE);
            return;
        }
        mainImage.setImageResource(peopleImages.get(currentIndex));
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            System.out.println("on fling");
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY) &&
                        Math.abs(diffX) > SWIPE_THRESHOLD &&
                        Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        animateSwipe(imageContainer, getScreenWidth(MainActivity.this));
                        likeFeedbackText.setText("LIKE!");
                        likeFeedbackText.setTextColor("#36EF3F");
                        backgroundLikeFeedback.setBackgroundColor("#2db732");
                    } else {
                        animateSwipe(imageContainer, -getScreenWidth(MainActivity.this));
                        likeFeedbackText.setText("DISLIKE!");
                        likeFeedbackText.setTextColor("#EF3636");
                        backgroundLikeFeedback.setBackgroundColor("#CB3535");
                    }
                    return true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }

}

