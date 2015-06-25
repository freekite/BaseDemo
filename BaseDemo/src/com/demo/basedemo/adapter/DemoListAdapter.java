package com.demo.basedemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.basedemo.R;

public class DemoListAdapter extends BaseAdapter{

	private List<String> dataList;
	private Context context;
	
	
	public DemoListAdapter(List<String> dataList, Context context) {
		super();
		this.dataList = dataList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
        if(convertView==null){
        	convertView= LayoutInflater.from(context).inflate(R.layout.item_demo_list,null);
            holder=new Holder();
			holder.textView1=(TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);
        }else {
            holder = (Holder)convertView.getTag();
        }
        holder.textView1.setText(dataList.get(position));
		return convertView;
	}
	class Holder{
		TextView textView1;
	}

}
