package com.scut.initial;

import java.util.HashMap;
import java.util.Map;

import itat.zttc.login.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.scut.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
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

			public void onClick(View arg0) 
			{
				RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
				StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://localhost:8080/xq/user_login.action",
				    new Response.Listener<String>() {
				        @Override
				        public void onResponse(String response) {
				        	//接收返回参数
				        }
				    }, new Response.ErrorListener() {
				        @Override
				        public void onErrorResponse(VolleyError error) {
				            //请求错误
				        }
				    }) {
				    @Override
				    protected Map<String, String> getParams() {
				        //在这里设置需要post的参数
				            Map<String, String> map = new HashMap<String, String>();  
				            map.put("name1", "value1");  
				            map.put("name2", "value2");  
				          return map;
				    }
				};        
				requestQueue.add(stringRequest);
			}
		});
	}

	// 控件初始化代码
	private void init() {
		button = (Button) findViewById(R.id.button1);
		register = (TextView) findViewById(R.id.register);
		findpassword = (TextView) findViewById(R.id.findpassword);
		usernameEditText = (EditText) findViewById(R.id.login_username);
		passwordEditText = (EditText) findViewById(R.id.login_password);
	}

	
}
