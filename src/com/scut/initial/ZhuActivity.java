package com.scut.initial;

import com.scut.discovery.DiscoveryFragment;
import com.scut.information.InformationFragment;
import com.scut.me.MeFragment;
import com.scut.message.MessageFragment;

import itat.zttc.login.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**主页面的活动类（对应标题栏和导航栏）
 * 
 *
 */
public class ZhuActivity extends FragmentActivity implements OnClickListener {
	private static final int INFO = 0;
	private static final int MSG = 1;
	private static final int DISCOV = 2;
	private static final int ME = 3;
	
	private LinearLayout mLayoutInfo;			//资讯
	private LinearLayout mLayoutMsg;			//消息
	private LinearLayout mLayoutDiscovery;		//发现
	private LinearLayout mLayoutMe;				//我

	private ImageButton mImgInfo;
	private ImageButton mImgMsg;
	private ImageButton mImgDiscovery;
	private ImageButton mImgMe;

	private Fragment mTabInfo;
	private Fragment mTabMsg;
	private Fragment mTabDiscovery;
	private Fragment mTabMe;
	
	
	
	private TextView top_textTextView;		//标题栏文字

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initView();
		initEvent();
		setSelect(INFO);
	}

	private void initEvent() {
		mLayoutInfo.setOnClickListener(this);
		mLayoutMsg.setOnClickListener(this);
		mLayoutDiscovery.setOnClickListener(this);
		mLayoutMe.setOnClickListener(this);
	}

	private void initView() {
		mLayoutInfo = (LinearLayout) findViewById(R.id.id_tab_info);
		mLayoutMsg = (LinearLayout) findViewById(R.id.id_tab_msg);
		mLayoutDiscovery = (LinearLayout) findViewById(R.id.id_tab_disc);
		mLayoutMe = (LinearLayout) findViewById(R.id.id_tab_me);

		mImgInfo = (ImageButton) findViewById(R.id.id_tab_info_img);
		mImgMsg = (ImageButton) findViewById(R.id.id_tab_msg_img);
		mImgDiscovery = (ImageButton) findViewById(R.id.id_tab_disc_img);
		mImgMe = (ImageButton) findViewById(R.id.id_tab_me_img);
		
		top_textTextView=(TextView) findViewById(R.id.top_text);
	}

	private void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// 把图片设置为亮的
		// 设置内容区域
		switch (i) {
		case INFO:
			if (mTabInfo == null) {//如果为空则添加该Fragment实例进transaction中，否则显示该Fragment
				mTabInfo = new InformationFragment();
				transaction.add(R.id.id_frag_main, mTabInfo);
			} else {
				transaction.show(mTabInfo);
			}
			mImgInfo.setImageResource(R.drawable.tab_info_pressed);
			break;
		case MSG:
			if (mTabMsg == null) {
				mTabMsg = new MessageFragment();
				transaction.add(R.id.id_frag_main, mTabMsg);
			} else {
				transaction.show(mTabMsg);

			}
			mImgMsg.setImageResource(R.drawable.tab_find_frd_pressed);
			break;
		case DISCOV:
			if (mTabDiscovery == null) {
				mTabDiscovery = new DiscoveryFragment();
				transaction.add(R.id.id_frag_main, mTabDiscovery);
			} else {
				transaction.show(mTabDiscovery);
			}
			mImgDiscovery.setImageResource(R.drawable.tab_address_pressed);
			break;
		case ME:
			if (mTabMe == null) {
				mTabMe = new MeFragment();
				transaction.add(R.id.id_frag_main, mTabMe);
			} else {
				transaction.show(mTabMe);
			}
			mImgMe.setImageResource(R.drawable.tab_me_pressed);
			break;

		default:
			break;
		}

		transaction.commit();
	}
	/**隐藏Fragment
	 * @param transaction
	 */
	private void hideFragment(FragmentTransaction transaction) {
		if (mTabInfo != null) {
			transaction.hide(mTabInfo);
		}
		if (mTabMsg != null) {
			transaction.hide(mTabMsg);
		}
		if (mTabDiscovery != null) {
			transaction.hide(mTabDiscovery);
		}
		if (mTabMe != null) {
			transaction.hide(mTabMe);
		}
	}

	@Override
	public void onClick(View v) {
		resetImgs();
		//设置标题栏文字
		switch (v.getId()) {
		case R.id.id_tab_info:
			setSelect(INFO);
			top_textTextView.setText("资讯");
			break;
		case R.id.id_tab_msg:
			setSelect(MSG);
			top_textTextView.setText("消息");
			break;
		case R.id.id_tab_disc:
			setSelect(DISCOV);
			top_textTextView.setText("发现");
			break;
		case R.id.id_tab_me:
			setSelect(ME);
			top_textTextView.setText("我");
			break;

		default:
			break;
		}
	}

	/**
	 * 切换图片至暗色
	 */
	private void resetImgs() {
		mImgInfo.setImageResource(R.drawable.tab_info_normal);
		mImgMsg.setImageResource(R.drawable.tab_find_frd_normal);
		mImgDiscovery.setImageResource(R.drawable.tab_address_normal);
		mImgMe.setImageResource(R.drawable.tab_me_normal);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			this.showTips();
			return false;
		}
		return false;
	}

	/**退出提醒
	 * 
	 */
	private void showTips() {
		AlertDialog alertDialog = new AlertDialog.Builder(ZhuActivity.this)
				.setTitle("退出程序").setMessage("是否退出程序")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						ZhuActivity.this.finish();//不能完全关闭所有Activity
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				}).create(); // 创建对话框
		alertDialog.show(); // 显示对话框
	}

}
