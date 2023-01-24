package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yantai.Database.Dao;
import com.example.yantai.Database.UserDatabase;
import com.example.yantai.Table.User;
import com.example.yantai.Tool.SP;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_login_name,Et_login_pass;
    private Button Bt_login;
    private String sc_num,sc_password;
    private CheckBox cb_pass,cb_login;
    private boolean aBoolean,bBoolean;
    private RadioGroup rg_user;
    private RadioButton rb_stu,rb_tea,rb_admin;
    private String user,password;
    private TextView textView;
    private SP sp;
    private Boolean login,rem;
    private List<User> list;
    private UserDatabase userDatabase;
    private Dao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        initListener();
        user = sp.getUserName();
        password = sp.getUserPassword();
        login = sp.getLogin();
        rem = sp.getRem();
        if (rem == true){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
            cb_login.setChecked(true);
        }else {
            if (login == true) {
                Et_login_name.setText(user);
                Et_login_pass.setText(password);
                cb_pass.setChecked(true);
                aBoolean = true;
            } else {

            }
            if (getIntent().getStringExtra("name") == null) {

            } else {
                user = getIntent().getStringExtra("name");
                password = getIntent().getStringExtra("password");
                Et_login_name.setText(user);
                Et_login_pass.setText(password);
            }
        }
    }
    public void initView(){
        Et_login_name = findViewById(R.id.login_name);
        Et_login_pass = findViewById(R.id.login_password);
        Bt_login = findViewById(R.id.login_button);
        cb_pass = findViewById(R.id.cb_password);
        cb_login = findViewById(R.id.cb_login);
        textView = findViewById(R.id.register);
        sp = new SP(getApplicationContext());
        userDatabase = UserDatabase.getDatabase(this);
        dao = userDatabase.getDao();
        list = new ArrayList<>();
        aBoolean = false;
        bBoolean = false;
    }
    public void initListener() {
        textView.setOnClickListener(this);
        Bt_login.setOnClickListener(this);
        cb_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb_pass.isChecked()){
                    aBoolean = true;
                }else {
                    aBoolean = false;
                }
            }
        });
        cb_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb_login.isChecked()){
                    bBoolean = true;
                }else {
                    bBoolean = false;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                getText();
                if (sc_num.isEmpty()||sc_password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"请输出完整信息",Toast.LENGTH_SHORT).show();
                }else {
                    QueryName(sc_num,sc_password);
                }
                break;
            case R.id.register:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
                break;
        }

    }
    public void getText(){
        sc_num = Et_login_name.getText().toString();
        sc_password = Et_login_pass.getText().toString();
    }
    public void QueryName(String name,String password){
        list = dao.queryUser(name);
        if (list.size()==0){
            Toast.makeText(this, "请先注册", Toast.LENGTH_SHORT).show();
        }else{
            if (name.equals(list.get(0).getName())&&password.equals(list.get(0).getMajor())){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                sp.SaveUser(name,password,aBoolean);
                sp.setRem(bBoolean);
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }else {
                Toast.makeText(this, "填写信息错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
}