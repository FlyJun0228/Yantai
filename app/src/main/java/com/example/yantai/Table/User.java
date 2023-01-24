package com.example.yantai.Table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
    //这里不写就默认将类名作为表名
        @PrimaryKey(autoGenerate = true)//自增长的行号id
        private  int id;
        @ColumnInfo(name = "name") //这个标注可以理解为列,后面的名称通常是来匹配网络传输的数据的
        private String name;
        @ColumnInfo(name = "num")
        private String num;
        @ColumnInfo(name = "sex")
        private String sex;
        @ColumnInfo(name = "major")
        private String major;


        //还需要一个有参构造
        public User(String name, String num, String sex, String major) {
           this.name = name;
           this.num = num;
           this.sex = sex;
           this.major = major;
        }

        //注意，一个类只能有一个有参构造，如果添加无参或者其他方法，要用@Ignore标志这个字段，意思是不将这个作为列
        //比如
        @Ignore
        public User() {
        }

        //还需要对应的get和set

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getMajor() {
            return major;
        }

        public String getNum() {
            return num;
        }


}
