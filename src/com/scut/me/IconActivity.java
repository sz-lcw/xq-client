package com.scut.me;


import com.scut.me.Me_Persion_Setting;

import itat.zttc.login.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class IconActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle("…Ë÷√Õ∑œÒ");
		
		setContentView(R.layout.me_icon);
		ImageButton img1 = (ImageButton)this.findViewById(R.id.img1);
		ImageButton img2 = (ImageButton)this.findViewById(R.id.img2);
		ImageButton img3 = (ImageButton)this.findViewById(R.id.img3);
		ImageButton img4 = (ImageButton)this.findViewById(R.id.img4);
	
		img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IconActivity.this,Me_Persion_Setting.class);
				intent.putExtra("pic",1);
				startActivity(intent);
			}
		});
    }
}

