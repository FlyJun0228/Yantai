package com.example.yantai.Table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)//自增长的行号id
    private  int id;
    @ColumnInfo(name = "title") //这个标注可以理解为列,后面的名称通常是来匹配网络传输的数据的
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "author")
    private String author;
public Note(String title,String content,String time,String author){
    this.title = title;
    this.content = content;
    this.time = time;
    this.author = author;
}
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }
}
