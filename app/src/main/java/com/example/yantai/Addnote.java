package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yantai.Database.NoteDao;
import com.example.yantai.Database.NoteDatabase;
import com.example.yantai.Table.Note;
import com.example.yantai.Tool.SP;
import com.example.yantai.Tool.Time;

public class Addnote extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_title,ed_content;
    private String title,content;
    private Button bt_add;
    private NoteDatabase noteDatabase;
    private NoteDao noteDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        initView();
        initListener();
    }
    public void initView(){
        ed_title = findViewById(R.id.title);
        ed_content = findViewById(R.id.content);
        bt_add = findViewById(R.id.add_wenzhang);
        noteDatabase = NoteDatabase.getDatabase(this);
        noteDao = (NoteDao) noteDatabase.getDao();
    }
    public void initListener(){
        bt_add.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_wenzhang:
               getText();
               if (title.isEmpty()||content.isEmpty()){
                   Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
               }else {
                   Add(title,content,new Time().getTime(),new SP(getApplicationContext()).getUserName());
               }
                break;

        }
    }
    public void Add(String title,String content,String time,String author){
        Note note = new Note(title,content,time,author);
        noteDao.insertNote(note);
        Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(Addnote.this,MainActivity.class);
        Addnote.this.startActivity(intent);
        finish();
    }
    public void getText(){
        title = ed_title.getText().toString();
        content = ed_content.getText().toString();
    }
}