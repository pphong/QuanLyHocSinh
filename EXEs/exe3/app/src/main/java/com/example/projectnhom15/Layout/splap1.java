package com.example.projectnhom15.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.projectnhom15.R;

import pl.droidsonroids.gif.GifImageView;

public class splap1 extends AppCompatActivity {
    private static int Splash_time_out = 3000;
    ImageView gifopen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splap1);
        gifopen = findViewById(R.id.gifopen);

        // nạp ảnh
        AnimationDrawable frameAnimation = (AnimationDrawable) gifopen.getDrawable();
        frameAnimation.start();
      //  overridePendingTransition(R.anim.move, R.anim.left);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        gifopen.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splap1.this,animationOpen.class);
                startActivity(intent);
                finish();
            }
        },Splash_time_out);

    }
}