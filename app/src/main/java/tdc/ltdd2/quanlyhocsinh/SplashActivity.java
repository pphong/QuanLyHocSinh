package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ImageView ivBook;
    ProgressBar pbProcess;
    Animation ani_movedown;
    private Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setEvent();
        setAniamtion();
        setControl();
    }

    private void setAniamtion() {
        ani_movedown = AnimationUtils.loadAnimation(this,R.anim.ani_movedown);
    }

    private void setEvent() {
        ivBook = (ImageView) findViewById(R.id.ivBook);
        pbProcess = (ProgressBar) findViewById(R.id.progressBar);
        main = new Intent(this, MainActivity.class);
    }

    private void setControl() {
        ivBook.startAnimation(ani_movedown);
        CountDownTimer countDownTimer = new CountDownTimer(5000,100) {
            @Override
            public void onTick(long l) {
                int proc = pbProcess.getProgress();
                pbProcess.setProgress(proc+2);
            }

            @Override
            public void onFinish() {
                int proc = pbProcess.getProgress();
                pbProcess.setProgress(proc + (100 - proc));
                startActivity(main);
            }
        };
        countDownTimer.start();
    }
}