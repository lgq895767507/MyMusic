package com.cc.mynote;

import com.cc.mynote.beans.Note;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

//便器为单页面操作，所以不需要返回父亲Activity,也不需要任何图标
//BaseActivity共开出两个方法一个是init,一个是doing,主要是添加和修改不一样
public abstract class BaseActivity extends Activity {

	// 便签内容文本框
	protected EditText info;
	protected Note note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);

		info = (EditText) findViewById(R.id.info);
		init();
	}

	public abstract void init();

	public abstract void doing();

	// 界面返回的时候处理任务
	@Override
	public void finish() {
		// 处理任务
		doing();
		super.finish();
	}

}
