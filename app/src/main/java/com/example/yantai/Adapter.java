package com.example.yantai;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class Adapter extends FragmentPagerAdapter {
    private List<Fragment> views;
    public Adapter(FragmentManager fragmentManager, List<Fragment> list){
        super(fragmentManager);
        this.views = list;
    }
    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }
}