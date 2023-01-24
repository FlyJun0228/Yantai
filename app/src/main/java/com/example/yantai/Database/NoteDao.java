package com.example.yantai.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yantai.Table.Note;
import com.example.yantai.Table.User;

import java.util.List;
@Dao
public interface NoteDao {
    //这里用SQL语句作为注解，让系统为我们生成对应的代码来匹配我们的方法
    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);
   // @Query("DELETE  FROM User where id=:id")
   // void  deleteWords(int id);
    //@Delete
    //void deleteUser(User user);

    @Query("select * from Note")
    List<Note> queryNote();
    //@Query("Update User set name=:name ,num=:num ,major=:major,sex=:sex where id=:id")
    //void updateUser(String name,int num,String major,String sex,int id);
    //还可以返回LiveData类型
    //@Query("select * from User")
    // LiveData<List<User>> queryAllBooks();
}
