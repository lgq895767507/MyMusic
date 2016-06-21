package com.cc.mynote.adapter;


import java.util.ArrayList;

import com.cc.mynote.EditActivity;
import com.cc.mynote.R;
import com.cc.mynote.beans.Note;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class NoteAdapter extends BaseAdapter{

	private ArrayList<Note> datas;
	private Context context;
	
	public NoteAdapter(ArrayList<Note> datas,Context context){
		this.datas = datas;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			
			convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
			viewHolder.info = (TextView) convertView.findViewById(R.id.info);
			viewHolder.st = (TextView) convertView.findViewById(R.id.st);

			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.info.setText(datas.get(position).getInfo());
		viewHolder.st.setText(datas.get(position).getSt());

		
		return convertView;
	}
	
	public class ViewHolder{
		private TextView info;
		private TextView st;

	}

	
}
