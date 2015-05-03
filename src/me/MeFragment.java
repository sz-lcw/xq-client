package me;

import me.Me_Persion_Setting;
import me.Setting;
import itat.zttc.login.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MeFragment extends Fragment {
	private Button settingButton;
	private Button persionSettingButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)

	{
		final View view = inflater.inflate(R.layout.me, container, false);
		settingButton = (Button) view.findViewById(R.id.tab04_setting);
		settingButton();

		persionSettingButton = (Button) view
				.findViewById(R.id.tab04_persionSetting);
		persionSettingButton();

		return view;

	}

	private void persionSettingButton() {
		persionSettingButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Me_Persion_Setting.class);
				startActivity(intent);
			}
		});

	}

	private void settingButton() {
		settingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Setting.class);
				startActivity(intent);
			}
		});
	}

}
