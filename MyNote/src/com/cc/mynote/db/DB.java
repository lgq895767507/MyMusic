package com.cc.mynote.db;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

public class DB extends SQLiteOpenHelper {
    //必须创建构造方法
    //数据库名
    static String name = "note.db";
    //游标工厂
    static SQLiteDatabase.CursorFactory factory = null;
    //数据库版本
    static int version = 1;
    public DB(Context context) {
        super(context, name, factory, version);
    }

    //必须重写【如果数据库存储则不允许，否则运行，用于创建数据库】
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库SQL语句
        db.execSQL("CREATE TABLE [note] (\n" +
                "  [id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "  [info] TEXT, \n" +
                "  [st] VARCHAR(50));");
        //你还可以添加一些初始数据
        String temp=DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date().getTime()).toString();
        db.execSQL("insert into note (info,st) values('欢迎使用便签APP', '"+temp+"')");
    }

    //必须重写【如果有数据库版本变更才会执行】
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}