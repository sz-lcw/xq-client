package me;

import itat.zttc.login.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Me_Persion_Setting extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.persion_setting1);
	}
}
