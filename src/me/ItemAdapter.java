package me;

import itat.zttc.login.R;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {
	

	private int resourceId;
	
	public ItemAdapter(Context context, int textViewResourceId,
			List<Item> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Item item = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView itemImage = (ImageView)view.findViewById(R.id.item_image);
		TextView  itemName = (TextView)view.findViewById(R.id.item_name);
		itemImage.setImageResource(item.getImageId());
		itemName.setText(item.getName());
		return view;
	}
	
}
