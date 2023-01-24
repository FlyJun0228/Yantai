package com.example.yantai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yantai.Database.Dao;
import com.example.yantai.Database.UserDatabase;
import com.example.yantai.Table.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_major,Et_glass,Et_grade,Et_name,Et_password,Et_yanzhengma;
    private Button Bt_add_student,Bt_yanzheng;
    private String stu_num,stu_major,stu_class,stu_grade,stu_name,stu_pass,sex,ma,ma1;
    private RadioGroup radioGroup;
    private UserDatabase userDatabase;
    private Dao dao;
    private NotificationManager manager;
    private List<String> list;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
        sex = "男";
    }
    public void initView(){
        Et_glass = findViewById(R.id.stu_class);
        Et_major = findViewById(R.id.stu_major);
        Et_name = findViewById(R.id.stu_name);
        Et_yanzhengma = findViewById(R.id.stu_yanzheng);
        Bt_add_student = findViewById(R.id.add_student);
        Bt_yanzheng = findViewById(R.id.ma);
        radioGroup = findViewById(R.id.rg_sex);
        Et_password = findViewById(R.id.stu_pass);
        userDatabase = UserDatabase.getDatabase(this);
        dao = userDatabase.getDao();

    }
    public void initListener(){
        Bt_add_student.setOnClickListener(this);
        Bt_yanzheng.setOnClickListener(this);
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
        ma = Et_yanzhengma.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_student:
                getText();
                if (stu_major.isEmpty()||stu_class.isEmpty()||stu_name.isEmpty()||stu_pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
                }else if (stu_pass.equals(stu_class)){
                    if (ma.isEmpty()){
                        Toast.makeText(this, "请填写验证码", Toast.LENGTH_SHORT).show();
                    }else if (ma.equals(ma1)){
                        AddUser();
                    }else {
                        Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.settings:

                break;
           case R.id.ma:
                ma1 = getRan();
                Notification(ma1);
                //Bt_yanzheng.setClickable(false);
               // Bt_yanzheng.setText("30秒后再试");
                /*Timer timer=new Timer();
                TimerTask tast=new TimerTask() {
                    @Override
                    public void run(){
                        Bt_yanzheng.setClickable(true);
                        Bt_yanzheng.setText("发送验证码");
                    }
                };
                timer.schedule(tast,5000);//

*/

                break;

        }
    }
    public void AddUser(){
        User user = new User(stu_name,stu_major,sex,stu_class);
        dao.insertUser(user);
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
       finish();
    }
    public void Notification(String text){
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        String channelId = "chat";
        String channelName = "聊天消息";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setAutoCancel(true)
                .setContentTitle("验证码")
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.yantai_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        manager.notify(1, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance)
    {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager)getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
    public String getRan(){
        int min=100000;
        int max=999999;
        Random random = new Random();
        int num = random.nextInt(max)%(max-min+1) + min;
        return String.valueOf(num);
    }
}