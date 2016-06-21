package com.cc.mynote;

import com.cc.mynote.beans.Note;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

//����Ϊ��ҳ����������Բ���Ҫ���ظ���Activity,Ҳ����Ҫ�κ�ͼ��
//BaseActivity��������������һ����init,һ����doing,��Ҫ����Ӻ��޸Ĳ�һ��
public abstract class BaseActivity extends Activity {

	// ��ǩ�����ı���
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

	// ���淵�ص�ʱ��������
	@Override
	public void finish() {
		// ��������
		doing();
		super.finish();
	}

}
