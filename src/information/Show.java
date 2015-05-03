package information;

import data.News;
import itat.zttc.login.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Show extends Activity implements OnClickListener{
	private Button returnButton;
	private Button applyBtn;
	private News news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.information_list_show);
		// 传值
		Intent intent = getIntent();
		String stringE = intent.getStringExtra("a");
		TextView text = (TextView) findViewById(R.id.title);
		text.setText(stringE);

		init();
		getData();
		// 为返回键添加监听事件
		buttonListener();
	}

	private void buttonListener() {
		returnButton.setOnClickListener(this);
		applyBtn.setOnClickListener(this);
		
	}

	private void init() {
		returnButton = (Button) findViewById(R.id.return_btn);
		applyBtn = (Button) findViewById(R.id.apply_activity_btn);
	}

	public static void actionStart() {

	}

	// 获取数据
	private void getData() {		
		
		
		//开始时间
		TextView textBeginTime = (TextView) findViewById(R.id.begin_time);
		//结束时间
		TextView textEndTime = (TextView) findViewById(R.id.end_time);
		//报名开始
		TextView textApplyTime = (TextView) findViewById(R.id.apply_time);
		//报名结束
		TextView textApplyEnd = (TextView) findViewById(R.id.apply_end);
		//报名人数
		TextView textApplyNum = (TextView) findViewById(R.id.apply_num);
		textBeginTime.setText("2015-04-30" + " " + "23:38");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_btn:
			onBackPressed();
			break;
		case R.id.apply_activity_btn:
			Apply.actionStart(Show.this);
			break;
		}
		
	}


}
