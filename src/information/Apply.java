package information;

import itat.zttc.login.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Apply extends Activity implements OnClickListener{
	private Button returnBtn;
	private Button applyBtn;
	private EditText applyNamEdT;
	private EditText applyTelEdT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.information_apply);
		init();//��ʼ��
		buttonListener();//��ť����
		transmit();//���ݴ���

		
	}
	
	private void transmit() {
		// TODO Auto-generated method stub
		String name = applyNamEdT.getText().toString();//����������
		String Tel = applyTelEdT.getText().toString();//�����ߵ绰
		//���ݴ��䣿
	}

	private void init() {
		// TODO Auto-generated method stub
		returnBtn = (Button)findViewById(R.id.return_activity_btn);
		applyBtn = (Button)findViewById(R.id.apply_btn);
		applyNamEdT = (EditText)findViewById(R.id.apply_name);
		applyTelEdT = (EditText)findViewById(R.id.apply_tel);
	}

	public static void actionStart(Context context){
		Intent intent = new Intent(context,Apply.class);
		
		context.startActivity(intent);
	}
	
	private void buttonListener() {
		returnBtn.setOnClickListener(this);
		applyBtn.setOnClickListener(this);
		
	}	
	public Apply() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_activity_btn:
			onBackPressed();
			break;
		case R.id.apply_btn:
			
			break;
		}
		
	}

}
