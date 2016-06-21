package com.cc.mynote.db;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

public class DB extends SQLiteOpenHelper {
    //���봴�����췽��
    //���ݿ���
    static String name = "note.db";
    //�α깤��
    static SQLiteDatabase.CursorFactory factory = null;
    //���ݿ�汾
    static int version = 1;
    public DB(Context context) {
        super(context, name, factory, version);
    }

    //������д��������ݿ�洢�������������У����ڴ������ݿ⡿
    @Override
    public void onCreate(SQLiteDatabase db) {
        //�������ݿ�SQL���
        db.execSQL("CREATE TABLE [note] (\n" +
                "  [id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "  [info] TEXT, \n" +
                "  [st] VARCHAR(50));");
        //�㻹�������һЩ��ʼ����
        String temp=DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date().getTime()).toString();
        db.execSQL("insert into note (info,st) values('��ӭʹ�ñ�ǩAPP', '"+temp+"')");
    }

    //������д����������ݿ�汾����Ż�ִ�С�
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}