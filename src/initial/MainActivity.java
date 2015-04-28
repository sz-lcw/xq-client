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
		//初始化控件
		init();
		
		//点击事件
		loginButton();
		registerButton();
		findpasswordButton();
	}


	
	private void findpasswordButton() {
		// 给找回密码添加监听事件
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
		// 给注册添加监听事件
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
		// 给登录按钮添加监听事件
		button.setOnClickListener(new OnClickListener() {

			
			public void onClick(View arg0) {
				
			if((!usernameEditText.getText().toString().isEmpty())&&(!passwordEditText.getText().toString().isEmpty())){
 
				loginThread=new Thread(new Runnable() {
					@Override
					public void run() {
					    JSONObject data = new JSONObject();
			            try {
			                //将用户填写的账号和密码存放到JSONObject中
			                data.put("username",usernameEditText.getText().toString());
			                data.put("password", passwordEditText.getText().toString());
			            } catch (JSONException e1) {
			                e1.printStackTrace();
			            }
			            
			          
			            
                        //获取登录验证结果
			           /* String result=HttpUtil.eXchangeDataByHttp(data,"/ClientLogin");*/
			           
			            String result="Correct";
			           /* 进行登录验证*/
			            if(result.equals("Correct")){
			            	handler.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(MainActivity.this,
											 "登录成功",
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
											 "用户名或密码错误",
											 Toast.LENGTH_LONG).show();
								}
							});
						}   
					}
				});
                loginThread.start();
			}else {
				 Toast.makeText(MainActivity.this,
						 "用户名或密码或密码不能为空",
						 Toast.LENGTH_LONG).show();
			}
			}
		});
	}

	
	
	// 控件初始化代码
	private void init() {
		button = (Button) findViewById(R.id.button1);
		register = (TextView) findViewById(R.id.register);
		findpassword = (TextView) findViewById(R.id.findpassword);
		usernameEditText=(EditText) findViewById(R.id.login_username);
		passwordEditText=(EditText) findViewById(R.id.login_password);
	}
	//关闭线程
	@Override
	protected void onDestroy() {
		if(loginThread!=null)
			loginThread=null;
		super.onDestroy();
	}
}
