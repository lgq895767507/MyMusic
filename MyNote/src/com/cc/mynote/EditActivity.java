package com.cc.mynote;

import java.util.Date;

import android.content.Intent;

import com.cc.mynote.beans.Note;

public class EditActivity extends BaseActivity {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		note = (Note) getIntent().getSerializableExtra("note");
		info.setText(note.getInfo());
	}

	@Override
	public void doing() {
		// TODO Auto-generated method stub
		note.setInfo(info.getText().toString());
		Intent intent = new Intent();
		intent.putExtra("note", note);
		setResult(MainActivity.EDIT, intent);
	}

}
