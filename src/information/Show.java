package information;

import itat.zttc.login.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Show extends Activity {
   private Button returnButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.information_list_show);
        //传值
		Intent intent=getIntent();
		String stringE=intent.getStringExtra("a");
		TextView text=(TextView)findViewById(R.id.title);
		text.setText(stringE);
		
		init();
		getData();
		//为返回键添加监听事件
		clickReturnButton();
	}


	private void clickReturnButton() {
	    returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {  
				onBackPressed();
			}
		});
	}


	private void init() {
		returnButton=(Button) findViewById(R.id.return_button);
	}
	
	public static void actionStart(){
		
	}
	//获取数据
	private void getData(){
		//启动时间
		TextView textBeginTime=(TextView)findViewById(R.id.begin_time);
		textBeginTime.setText("2015-04-30" + " " + "23:38");
		
	}
	
}
