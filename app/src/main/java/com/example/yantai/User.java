package com.example.yantai;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yantai.Database.Dao;
import com.example.yantai.Database.UserDatabase;
import com.example.yantai.Tool.SP;

import java.util.List;
import java.util.Random;

public class User extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_major,Et_glass,Et_grade,Et_name,Et_password;
    private Button Bt_add_student;
    private String stu_num,stu_major,stu_class,stu_grade,stu_name,stu_pass,sex;
    private RadioGroup radioGroup;
    private UserDatabase userDatabase;
    private Dao dao;
    private List<com.example.yantai.Table.User> list;
    private int id;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initListener();
        setUser();
    }
    public void initView(){
        Et_glass = findViewById(R.id.stu_class);
        Et_major = findViewById(R.id.stu_major);
        Et_name = findViewById(R.id.stu_name);
        Bt_add_student = findViewById(R.id.update_student);
        radioGroup = findViewById(R.id.rg_sex);
        Et_password = findViewById(R.id.stu_pass);
        userDatabase = UserDatabase.getDatabase(this);
        dao = userDatabase.getDao();
    }
    public void setUser(){
        list = dao.queryUser(new SP(getApplicationContext()).getUserName());
        Et_name.setText(list.get(0).getName());
        Et_major.setText(list.get(0).getNum());
        Et_password.setText(list.get(0).getMajor());
        Et_glass.setText(list.get(0).getMajor());
        sex = list.get(0).getSex();
        if (sex.equals("男")){
            radioGroup.check(R.id.male);
        }else {
            radioGroup.check(R.id.female);
        }
        id = list.get(0).getId();
    }
    public void UpdateUser(){
        dao.updateUser(stu_name,stu_major,stu_pass,sex,id);
        showTwo();

    }
    public void initListener(){
        Bt_add_student.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        sex = "男";
                        break;
                    case R.id.female:
                        sex = "女";
                        break;
                }
            }
        });
    }
    public void getText(){
        stu_class = Et_glass.getText().toString();
        stu_major = Et_major.getText().toString();
        stu_name = Et_name.getText().toString();
        stu_pass = Et_password.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.update_student:
                getText();
                if (stu_major.isEmpty()||stu_class.isEmpty()||stu_name.isEmpty()||stu_pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
                }else if (stu_pass.equals(stu_class)){
                    SP sp = new SP(getApplicationContext());
                    sp.SaveUser(stu_name,sp.getUserPassword(),sp.getLogin());
                    UpdateUser();
                }else {
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.settings:

                break;
        }
    }
    private void showTwo() {

        builder = new AlertDialog.Builder(this).setIcon(R.drawable.more).setTitle("信息修改")
                .setMessage("信息更新成功").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        Intent intent = new Intent();
                        intent.setClass(User.this, MainActivity.class);
                        User.this.startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

}