package com.example.projectnhom15.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.projectnhom15.R;

import pl.droidsonroids.gif.GifImageView;

public class animationOpen extends AppCompatActivity {
    private static int Splash_time_out = 4000;
    ImageView imgWelcome,imgManager,imgTeam;
    GifImageView cycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_open);
      //  overridePendingTransition(R.anim.zoomin, R.anim.zoomin);


        setControl();
        setEvent();

    }

    private void setEvent() {
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        imgWelcome.startAnimation(aniSlide);

        Animation aniSlide1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right);
        imgManager.startAnimation(aniSlide1);

        Animation aniSlide2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left);
        imgTeam.startAnimation(aniSlide2);

        Animation aniSlide3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        cycle.startAnimation(aniSlide3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(animationOpen.this,Login.class);
                startActivity(intent);
                finish();
            }
        },Splash_time_out);
    }

    private void setControl() {
        imgWelcome = findViewById(R.id.img_welcome);
        imgManager = findViewById(R.id.img_stdmn);
        imgTeam = findViewById(R.id.img_team15);
        cycle = findViewById(R.id.cycle);
    }
}