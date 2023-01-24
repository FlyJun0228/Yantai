package com.example.yantai.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yantai.Table.User;


@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase userDatabase;

    //创建一个方法，这个方法返回一个BookDatabase
    public static UserDatabase getDatabase(Context context) {
        if (userDatabase == null) {
            //如果为创建这个数据库，那么就呼叫builder来创建数据库
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, "User")
                    .allowMainThreadQueries()//这个是强制运行在主线程操作，这个一般不推荐，都是需要重开子线程来操作的
                    .build();
        }
        return userDatabase;
    }

    //另外我们还需要通过一个抽象方法来获取Dao
    public abstract Dao getDao();
}

