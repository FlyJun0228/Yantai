package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    private static final long DELAY = 2000;
    private TimerTask task;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.image);
        final Intent localIntent=new Intent(this,LoginActivity.class);//你要转向的Activity

        Animation animation = AnimationUtils.loadAnimation(

                Splash.this, R.anim.ani);
        animation.setDuration(1500);
        imageView.startAnimation(animation);
        Timer timer=new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
                startActivity(localIntent);//执行
                finish();
            }
        };
        timer.schedule(tast,DELAY);//

    }
}