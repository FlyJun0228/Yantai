package com.example.yantai.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.yantai.Table.Note;
import com.example.yantai.Table.User;

@Database(entities = {Note.class},version = 2,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase noteDatabase;
    //创建一个方法，这个方法返回一个BookDatabase
    public static NoteDatabase getDatabase(Context context) {
        if (noteDatabase == null) {
            //如果为创建这个数据库，那么就呼叫builder来创建数据库
            noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, "Note")
                    .allowMainThreadQueries()//这个是强制运行在主线程操作，这个一般不推荐，都是需要重开子线程来操作的
                    .build();
        }
        return noteDatabase;
    }
    //另外我们还需要通过一个抽象方法来获取Dao
    public abstract NoteDao getDao();

}
