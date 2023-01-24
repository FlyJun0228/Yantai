package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.VideoView;

public class Video extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mp;//mediaPlayer对象
    private Button bt_start,bt_next,bt_shang;
    private VideoView videoView;
    private int i;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initLsitener();
    }
    public void initView(){
        bt_start = findViewById(R.id.start_video);
        bt_next = findViewById(R.id.next_video);
        bt_shang = findViewById(R.id.shang_video);
        videoView = findViewById(R.id.imgvideo);
        //videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/raw/" +
               // R.raw.hokai));
    }
    public void initLsitener(){
        bt_shang.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_start.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_video:

                if (i%2==0){
                    bt_start.setBackgroundResource(R.drawable.pause);
                    videoView.start();
                }else {
                    bt_start.setBackgroundResource(R.drawable.start);
                    videoView.pause();
                }
                i++;
                break;
            case R.id.next_video:
               // videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/raw/" +
                      //  R.raw.zz));
                videoView.start();
                break;
            case R.id.shang_video:
               // videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/raw/" +
                      //  R.raw.hokai));
                videoView.start();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}