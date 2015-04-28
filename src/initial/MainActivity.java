package initial;

import itat.zttc.login.R;
import itat.zttc.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button button;
    private TextView register;
    private TextView findpassword;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Thread loginThread;
    private Handler handler=new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		init();
		
		//����¼�
		loginButton();
		registerButton();
		findpasswordButton();
	}


	
	private void findpasswordButton() {
		// ���һ�������Ӽ����¼�
		findpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, FindPassword.class);
				startActivity(intent);
			}
		});
	}

	private void registerButton() {
		// ��ע����Ӽ����¼�
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	private void loginButton() {
		// ����¼��ť��Ӽ����¼�
		button.setOnClickListener(new OnClickListener() {

			
			public void onClick(View arg0) {
				
			if((!usernameEditText.getText().toString().isEmpty())&&(!passwordEditText.getText().toString().isEmpty())){
 
				loginThread=new Thread(new Runnable() {
					@Override
					public void run() {
					    JSONObject data = new JSONObject();
			            try {
			                //���û���д���˺ź������ŵ�JSONObject��
			                data.put("username",usernameEditText.getText().toString());
			                data.put("password", passwordEditText.getText().toString());
			            } catch (JSONException e1) {
			                e1.printStackTrace();
			            }
			            
			          
			            
                        //��ȡ��¼��֤���
			           /* String result=HttpUtil.eXchangeDataByHttp(data,"/ClientLogin");*/
			           
			            String result="Correct";
			           /* ���е�¼��֤*/
			            if(result.equals("Correct")){
			            	handler.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(MainActivity.this,
											 "��¼�ɹ�",
											 Toast.LENGTH_LONG).show();
									Intent intent = new Intent();
									intent.setClass(MainActivity.this, ZhuActivity.class);
									startActivity(intent);
								}
							});
			            }else {
			            	handler.post(new Runnable() {
								@Override
								public void run() {
									 Toast.makeText(MainActivity.this,
											 "�û������������",
											 Toast.LENGTH_LONG).show();
								}
							});
						}   
					}
				});
                loginThread.start();
			}else {
				 Toast.makeText(MainActivity.this,
						 "�û�������������벻��Ϊ��",
						 Toast.LENGTH_LONG).show();
			}
			}
		});
	}

	
	
	// �ؼ���ʼ������
	private void init() {
		button = (Button) findViewById(R.id.button1);
		register = (TextView) findViewById(R.id.register);
		findpassword = (TextView) findViewById(R.id.findpassword);
		usernameEditText=(EditText) findViewById(R.id.login_username);
		passwordEditText=(EditText) findViewById(R.id.login_password);
	}
	//�ر��߳�
	@Override
	protected void onDestroy() {
		if(loginThread!=null)
			loginThread=null;
		super.onDestroy();
	}
}
