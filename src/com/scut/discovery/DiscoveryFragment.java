package com.scut.discovery;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import itat.zttc.login.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DiscoveryFragment extends Fragment {
	private  View view;
	private ImageView miImageView;
	ImageLoader mImageLoader;
	ImageListener mImageListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.discovery, container, false);
		/* miImageView=(ImageView) view.findViewById(R.id.testimageView1);
		 ImageListener listener = ImageLoader.getImageListener(miImageView, android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_dialog_alert);  
		 mImageLoader.get("http://192.168.1.104:8080/xq/image/5.jpg", listener); */		 
		 return view;
	}
}
