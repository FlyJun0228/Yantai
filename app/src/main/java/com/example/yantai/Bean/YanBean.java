package com.example.yantai.Bean;

public class YanBean {
    private String title;
    private String content;
    private int img;
    public YanBean(String title,String content,int img){
        this.title = title;
        this.content = content;
        this.img = img;

    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
