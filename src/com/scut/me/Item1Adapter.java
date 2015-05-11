package com.scut.me;

import itat.zttc.login.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Item1Adapter extends ArrayAdapter<Item1> {
	private int resourceId;
	public Item1Adapter(Context context, int textViewResourceId,
			List<Item1> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Item1 item1 = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView  itemInfo = (TextView)view.findViewById(R.id.item_info);
		TextView  itemDetail = (TextView)view.findViewById(R.id.item_detail);
		itemInfo.setText(item1.getInfo());
		itemDetail.setText(item1.getDetail());
		return view;
	}

}
