package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Show extends AppCompatActivity {
    private TextView title,content,time;
    private ImageView imageView;
    private String new_title,new_content,new_time,icon;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        getText();
        title.setText(new_title);
        content.setText(new_content);
        imageView.setImageResource(index);

    }
    public void initView(){
        title = findViewById(R.id.new_title);
        content = findViewById(R.id.new_content);
        imageView = findViewById(R.id.new_bg);
    }
    public void getText(){
        new_title = getIntent().getStringExtra("title");
        new_content = getIntent().getStringExtra("content");
        index = getIntent().getIntExtra("img",0);
    }
}