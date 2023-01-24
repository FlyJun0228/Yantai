package com.example.yantai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yantai.Fragment.One;
import com.example.yantai.Fragment.two;
import com.example.yantai.Tool.SP;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private LinearLayout navHeaderUserLayout;
    private TextView title;
    private int day = 0, month = 0;
    private ImageView menuButton;
    private FragmentTabHost fragmentTabHost;
    private View view = null;
    private LayoutInflater layoutInflater;
    private List<Tab> list = new ArrayList<Tab>(4);
    private SharedPreferences sharedPreferences;
    private ViewPager viewPager;
    private List<Fragment> FragmentList = new ArrayList<Fragment>(4);
    private AlertDialog.Builder builder;
    public static int i = 0;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        layoutInflater = LayoutInflater.from(this);
        initTab();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.settings:
                        Intent intent2 = new Intent();
                        intent2.setClass(MainActivity.this,User.class);
                        MainActivity.this.startActivity(intent2);
                        break;
                    case R.id.web:
                        Intent intent1 = new Intent();
                        intent1.setClass(MainActivity.this,Web.class);
                        MainActivity.this.startActivity(intent1);
                        break;
                    case R.id.quit:
                        Intent intent3 = new Intent();
                        intent3.setClass(MainActivity.this,LoginActivity.class);
                        new SP(getApplicationContext()).setLogin(false);
                        new SP(getApplicationContext()).setRem(false);
                        MainActivity.this.startActivity(intent3);
                        finish();
                        break;
                    case R.id.music:
                       Intent intent = new Intent();
                       intent.setClass(MainActivity.this,Media.class);
                       MainActivity.this.startActivity(intent);
                        break;
                    case R.id.video:
                        Intent intent4 = new Intent();
                        intent4.setClass(MainActivity.this,Video.class);
                        MainActivity.this.startActivity(intent4);
                        break;
                }
                return true;
            }
        });
    }

    public void initView() {
        menuButton = findViewById(R.id.menu_button);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        navView.getMenu().getItem(0).setChecked(true);
        navHeaderUserLayout = navView.getHeaderView(0)
                .findViewById(R.id.nav_header_username_layout);
        title = findViewById(R.id.nav_header_text);
        get();
    }

    public void initClick() {
        menuButton.setOnClickListener(this);
        navHeaderUserLayout.setOnClickListener(this);
        // monthTotalLayout.setOnClickListener(this);
    }

    public void get() {
        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH)+1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.menu_button:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

        }
    }

    private void initTab(){
        fragmentTabHost = (FragmentTabHost)findViewById(R.id.id_tabs);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.id_viewpager);
        Tab tabplan = new Tab(R.string.kuaidi,R.drawable.yantai, One.class);
        Tab Dingdan = new Tab(R.string.wode,R.drawable.more, two.class);
        list.add(tabplan);
        list.add(Dingdan);
        for (Tab tab : list){
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(getString(tab.getTitle()))
                    .setIndicator(getItemview(tab));
            fragmentTabHost.addTab(spec,tab.getFragment(),null);
        }
        fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        initFragment();
    }
    public void initFragment(){
        FragmentList.add(new One());
        FragmentList.add(new two());
        viewPager = (ViewPager)findViewById(R.id.id_viewpager);
        viewPager.setAdapter(new Adapter(getSupportFragmentManager(),FragmentList));
        viewPager.addOnPageChangeListener(new MyPageChangerListener(fragmentTabHost));
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int p = fragmentTabHost.getCurrentTab();
                viewPager.setCurrentItem(p);
            }
        });
    }
    private View getItemview(Tab tab){
        view = layoutInflater.inflate(R.layout.itemview,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.id_imageview);
        TextView textView = (TextView)view.findViewById(R.id.id_textview);
        imageView.setImageResource(tab.getImg());
        textView.setText(tab.getTitle());
        return view;
    }


}