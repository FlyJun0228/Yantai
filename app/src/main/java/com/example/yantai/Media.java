package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Media extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mp;//mediaPlayer对象
    private Button bt_start,bt_next,bt_shang;
    private int i;
    private ImageView imageView;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        initView();
        initLsitener();


    }
    public void initView(){
        bt_start = findViewById(R.id.start_music);
        bt_next = findViewById(R.id.next_music);
        bt_shang = findViewById(R.id.shang_music);
        imageView = findViewById(R.id.imgmusic);
        animation = AnimationUtils.loadAnimation(

                Media.this, R.anim.music);
        animation.setDuration(20000);
    }
    public void initLsitener(){
        bt_shang.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_start.setOnClickListener(this);
        //mp=MediaPlayer.create(getApplicationContext(), R.raw.zz);
        i =0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_music:
                if (i%2==0){
                   mp.start();
                    imageView.startAnimation(animation);
                    bt_start.setBackgroundResource(R.drawable.pause);
                }else {
                    imageView.clearAnimation();
                    mp.pause();
                    bt_start.setBackgroundResource(R.drawable.start);
                }
                i++;
                break;
            case R.id.next_music:
                mp.stop();
                mp=MediaPlayer.create(getApplicationContext(), R.raw.empire);
                mp.start();
                imageView.startAnimation(animation);
                break;
            case R.id.shang_music:
                mp.stop();
                //mp=MediaPlayer.create(getApplicationContext(), R.raw.zz);
                mp.start();
                imageView.startAnimation(animation);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mp.release();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}