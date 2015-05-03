package me;

import java.util.ArrayList;
import java.util.List;

import itat.zttc.login.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;

public class Me_Persion_Setting extends Activity {
	private List<Item> itemList = new ArrayList<Item>();
	Item1 nickname,address,gender,school;
	public Item hicon;
	private List<Item1> itemList1 = new ArrayList<Item1>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Win��dow.FEATURE_NO_TITLE);
		setTitle("������Ϣ");
		setContentView(R.layout.persion_setting1);
		initItem();
		final ItemAdapter adapter = new ItemAdapter(Me_Persion_Setting.this,
				R.layout.me_item, itemList);
		final Item1Adapter adapter1 = new Item1Adapter(Me_Persion_Setting.this,
				R.layout.me_item1, itemList1);
		
		ListView listView = (ListView) findViewById(R.id.list_view);
		ListView listView1 = (ListView) findViewById(R.id.list_view1);
		
		listView.setAdapter(adapter);
		listView1.setAdapter(adapter1);
		Intent intentrec = getIntent();
        int pic=0;
        pic = intentrec.getIntExtra("pic",0);
        if(pic==1)
        {
        	hicon.setImageId(R.drawable.apple_pic);
        	adapter.notifyDataSetChanged();
        	
        }
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Me_Persion_Setting.this,IconActivity.class);
				startActivity(intent);
                
			}
			
		});
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Item1 itemnum = itemList1.get(position);
				LayoutInflater layoutInflater = LayoutInflater.from(Me_Persion_Setting.this);
                View dialog = layoutInflater.inflate(R.layout.me_dialog, null);
                final EditText dia = (EditText)dialog.findViewById(R.id.dialog);
			  //Toast.makeText(Me_Persion_Setting.this, item1.getDetail(), Toast.LENGTH_SHORT).show();
                
				AlertDialog.Builder builder = new AlertDialog.Builder(Me_Persion_Setting.this);
				if(itemnum.getInfo()=="�ǳ�")
				{
				    builder.setTitle("���������ǳ�")
					.setPositiveButton("ȷ��",new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String val="Daniel";
				     		val = dia.getText().toString();
							nickname.setDetail(val);
							adapter1.notifyDataSetChanged();
							
						}
					} )
					.setNegativeButton("ȡ��", null)
					.setView(dialog)
					.show();			
				}
				if(itemnum.getInfo()=="ѧУ")
				{
				    builder.setTitle("������ѧУ")
					.setPositiveButton("ȷ��",new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String val;
				     		val = dia.getText().toString();
							school.setDetail(val);
							adapter1.notifyDataSetChanged();
						}
					} )
					.setNegativeButton("ȡ��", null)
					.setView(dialog)
					.show();			
				}
				if(itemnum.getInfo()=="�Ա�")
				{
				 builder.setTitle("��ѡ��")
				 .setSingleChoiceItems(new String[]{"��","Ů"},-1,new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String hint[]={"��","Ů"};
					//	Toast.makeText(Me_Persion_Setting.this, hint[which], Toast.LENGTH_SHORT).show();
						gender.setDetail(hint[which]);
						adapter1.notifyDataSetChanged();
						dialog.dismiss();
					}
				})
				 .show();
					
				}
			}
		});
	}
	 
	 private void initItem() {
		   
		   hicon = new Item("ͷ��", R.drawable.logimg);
			itemList.add(hicon);
			
			nickname = new Item1("�ǳ�","�������");
			itemList1.add(nickname);
		    gender = new Item1("�Ա�","��");
			itemList1.add(gender);
		 //   address = new Item1("��ַ","�㶫 ����");
		//	itemList1.add(address);
			school = new Item1("ѧУ","��������ѧ");
			itemList1.add(school);
		}
		
}
