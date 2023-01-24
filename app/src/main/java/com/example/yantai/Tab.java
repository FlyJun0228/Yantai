package com.example.yantai;

/**
 * Created by Administrator on 2017/4/16.
 */

public class Tab {
    private int title;
    private int img;
    private Class fragment;
    public Tab(int title, int img, Class fragment){
        this.title = title;
        this.img = img;
        this.fragment = fragment;
    }
    public int getTitle() {
        return title;
    }
    public void setTitle(int title){
        this.title = title;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img){
        this.img = img;
    }
    public Class getFragment(){
        return fragment;
    }
    public void setFragment(Class fragment){
        fragment = fragment;
    }
}
