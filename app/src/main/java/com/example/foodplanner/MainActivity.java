package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.MainScreen.View.HomeActivity;

public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    LottieAnimationView lottieAnimationView;
    TextView tvTitle, tvSlogan;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        lottieAnimationView = findViewById(R.id.animationView);



        tvTitle = findViewById(R.id.tv_title);
        tvSlogan = findViewById(R.id.tv_slogan);

        lottieAnimationView.setAnimation(topAnim);

        tvTitle.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },3200);

    }
}