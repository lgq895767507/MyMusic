package com.cc.mynote.db;

import java.util.ArrayList;

import com.cc.mynote.beans.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NoteDao {

	Context context;
	DB db;

	public NoteDao(Context context) {
		this.context = context;
		db = new DB(context);
	}

	// 添加方法
	public void insert(Note note) {
		SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("info", note.getInfo());
		contentValues.put("st", note.getSt());
		sqLiteDatabase.insert("note", null, contentValues);
		sqLiteDatabase.close();
	}

	// 查询所有方法
	public ArrayList<Note> selectByAll() {
		ArrayList<Note> temp = new ArrayList();
		SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(true, "note",
				new String[] { "id,info,st" }, null, null, null, null,
				"id desc", null);
		while (cursor.moveToNext()) {
			Note note = new Note();
			note.setId(cursor.getInt(0));
			note.setInfo(cursor.getString(1));
			note.setSt(cursor.getString(2));
			temp.add(note);
		}
		sqLiteDatabase.close();
		return temp;
	}

	// 删除方法

	public void del(int id) {
		SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
		// 第二个参数为查询的条件,id为字段名，?为占位符
		// 第三个参数?占位符的值
		int i = sqLiteDatabase.delete("note", "id=?",
				new String[] { String.valueOf(id) });
		Log.i("cc", "i:" + i);

		sqLiteDatabase.close();
	}

	// 修改方法
	public void update(Note note) {
		SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("info", note.getInfo());
		contentValues.put("st", note.getSt());
		sqLiteDatabase.update("note", contentValues, "id=?",
				new String[] { String.valueOf(note.getId()) });
		sqLiteDatabase.close();
	}

	// 按照ID查询方法
	public Note selectById(int id) {
		Note temp = new Note();
		SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(true, "note",
				new String[] { "id,info,st" }, "id=?",
				new String[] { String.valueOf(id) }, null, null, "id desc",
				null);
		while (cursor.moveToNext()) {
			temp.setId(cursor.getInt(0));
			temp.setInfo(cursor.getString(1));
			temp.setSt(cursor.getString(2));
		}
		sqLiteDatabase.close();
		return temp;
	}
}
