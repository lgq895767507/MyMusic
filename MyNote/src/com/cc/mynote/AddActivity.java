package com.cc.mynote;

import java.util.Date;

import android.content.Intent;
import android.text.format.DateFormat;

import com.cc.mynote.beans.Note;

public class AddActivity extends BaseActivity {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		note = new Note();
	}

	@Override
	public void doing() {
		// TODO Auto-generated method stub
		note.setInfo(info.getText().toString());
		Date date = new Date();
		String st = DateFormat.format("yyyy-MM-dd HH:mm:ss", date.getTime())
				.toString();
		note.setSt(st);
		Intent intent = new Intent();
		intent.putExtra("note", note);
		setResult(MainActivity.ADD, intent);
	}

}
