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
		// ��ֵ
		Intent intent = getIntent();
		String stringE = intent.getStringExtra("a");
		TextView text = (TextView) findViewById(R.id.title);
		text.setText(stringE);

		init();
		getData();
		// Ϊ���ؼ���Ӽ����¼�
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

	// ��ȡ����
	private void getData() {		
		
		
		//��ʼʱ��
		TextView textBeginTime = (TextView) findViewById(R.id.begin_time);
		//����ʱ��
		TextView textEndTime = (TextView) findViewById(R.id.end_time);
		//������ʼ
		TextView textApplyTime = (TextView) findViewById(R.id.apply_time);
		//��������
		TextView textApplyEnd = (TextView) findViewById(R.id.apply_end);
		//��������
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
