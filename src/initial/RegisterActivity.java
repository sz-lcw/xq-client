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

public class RegisterActivity extends Activity {
	private Button registerButton;
	private EditText username;
	private EditText password;
	private EditText mailbox;
	private EditText school;
	private Thread registerThread;
	private Handler registerHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_register);
		init();
		initregisterButton();

	}

	private void initregisterButton() {
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkInput();
				registerThread = new Thread(new Runnable() {
					@Override
					public void run() {
						JSONObject data = new JSONObject();
						try {
							// 将用户填写的账号和密码存放到JSONObject中
							data.put("username", username.getText().toString());
							data.put("password", password.getText().toString());
							data.put("mailbox", mailbox.getText().toString());
							data.put("school", school.getText().toString());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						// 获取注册结果
						String result = HttpUtil.eXchangeDataByHttp(data,
								"/register");
						System.out.println(result);
						if (result.equals("Correct")) {
							registerHandler.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(RegisterActivity.this,
											"注册成功", Toast.LENGTH_LONG).show();
									Intent intent = new Intent();
									intent.setClass(RegisterActivity.this,
											ZhuActivity.class);
									startActivity(intent);
								}
							});
						} else {
							registerHandler.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(RegisterActivity.this,
											"用户名已被占用,请更改用户名", Toast.LENGTH_LONG)
											.show();
								}
							});
						}
					}
				});
				registerThread.start();
			}

			private void checkInput() {
				String usString = username.getText().toString();
				String paString = password.getText().toString();
				if ("".equals(usString) && "".equals(paString)) {
					Toast.makeText(RegisterActivity.this, "用户名跟密码不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		});
	}

	private void init() {
		username = (EditText) this.findViewById(R.id.register_username);
		password = (EditText) this.findViewById(R.id.register_password);
		mailbox = (EditText) this.findViewById(R.id.register_mailbox);
		school = (EditText) this.findViewById(R.id.register_school);
		registerButton = (Button) this.findViewById(R.id.register);
	}

	@Override
	protected void onDestroy() {
		if (registerThread != null)
			registerThread = null;
		super.onDestroy();
	}
}
