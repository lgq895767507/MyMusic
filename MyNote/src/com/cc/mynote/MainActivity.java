package com.cc.mynote;

import java.util.ArrayList;

import com.cc.mynote.adapter.NoteAdapter;
import com.cc.mynote.beans.Note;
import com.cc.mynote.db.NoteDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity{

	private final static String TAG = "CC";
	// 添加的code
	public static final int ADD = 1;
	// 修改的code
	public static final int EDIT = 2;

	private ListView lvNote;
	private NoteAdapter adapter;
	private ArrayList<Note> datas;
	private NoteDao noteDao;
	private Button btnAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initView
		initView();

		// add note
		addNote();
		
		//itemListener
		onItem();
	}

	private void initView() {
		lvNote = (ListView) findViewById(R.id.lvNote);
		btnAdd = (Button) findViewById(R.id.btnAdd);

		noteDao = new NoteDao(this);

		datas = noteDao.selectByAll();

		Log.i(TAG, "datas:::" + datas.size());

		adapter = new NoteAdapter(datas, this);

		lvNote.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private void addNote() {
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), AddActivity.class);
				startActivityForResult(intent, ADD);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == ADD && resultCode == ADD) {
			Note temp = (Note) data.getSerializableExtra("note");
			if (temp.getInfo().trim().equals("")) {
				Toast.makeText(this, "便签内容不可以为空", Toast.LENGTH_LONG).show();
				return;
			}

			noteDao.insert(temp);
			datas.add(0, temp);
			// 更新
			adapter.notifyDataSetChanged();
		}
		if (requestCode == EDIT && resultCode == EDIT) {
			Note temp = (Note) data.getSerializableExtra("note");

			if (temp.getInfo().trim().equals("")) {
				Toast.makeText(this, "便签内容不可以为空", Toast.LENGTH_LONG).show();
				return;
			}

			noteDao.update(temp);
			int position = datas.indexOf(temp);
			datas.set(position, temp);
			// 更新
			adapter.notifyDataSetChanged();
		}
	}
	
	private void onItem(){
		lvNote.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i(TAG, "position:::"+position+"id:::"+id);
				Intent intent = new Intent(view.getContext(), EditActivity.class);
		        intent.putExtra("note", datas.get(position));
		        startActivityForResult(intent, EDIT);
			}
		});
		
		lvNote.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i(TAG, "longposition:::"+position+"longid:::"+id);
				final int tempPosition = position;
		        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		        builder.setTitle("删除确认");
		        builder.setMessage("你确实要删除吗?");    
		        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {

		                noteDao.del(datas.get(tempPosition).getId());
		                datas.remove(tempPosition);
		                //更新
		                adapter.notifyDataSetChanged();

		            }
		        });
		        builder.setNegativeButton("取消", null);
		        AlertDialog alertDialog = builder.create();
		        alertDialog.show();
		    
				
				
				return true;
			}
		});
	}
}
