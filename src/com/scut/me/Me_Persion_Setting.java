package com.scut.me;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


import itat.zttc.login.R;
import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;

public class Me_Persion_Setting extends Activity {
	private List<Item1> itemList1 = new ArrayList<Item1>();
	Item1 nickname,address,gender,school;
	private LinearLayout iconSet;  //ͷ���������������Բ��ִ���֮ǰ��ListView�����������ó�button��ʽ���ɵ��
	private ImageView iconImage;   //��ʾͷ���ImageView
	private String[] Iconitems = new String[] { "ѡ�񱾵�ͼƬ", "����" };

	/* ������ */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Win��dow.FEATURE_NO_TITLE);
		setTitle("������Ϣ");
		setContentView(R.layout.persion_setting1);
		initItem();                                            //��ʼ��ListView����ʾ�ǳơ�ѧУ����Ϣ
		ListView listView1 = (ListView) findViewById(R.id.list_view1);
		final Item1Adapter adapter1 = new Item1Adapter(Me_Persion_Setting.this,
		R.layout.me_item1, itemList1);
		final SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        final SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		listView1.setAdapter(adapter1);    
        iconSet = (LinearLayout) findViewById(R.id.iconset);       
        iconImage = (ImageView) findViewById(R.id.iconimage);
        String nicknameset = pref.getString("nickname", "");
        String schoolset = pref.getString("school", "");
        String genderset = pref.getString("gender", "");
        if(nicknameset!="")
        {
	        nickname.setDetail(nicknameset);
	        adapter1.notifyDataSetChanged();
        }else{
        	 nickname.setDetail("null");
 	        adapter1.notifyDataSetChanged();
        }
        if(schoolset!="")
        {
	        school.setDetail(schoolset);
	        adapter1.notifyDataSetChanged();
        }else{
        	 school.setDetail("null");
 	        adapter1.notifyDataSetChanged();
        }
        if(genderset!="")
        {
	        gender.setDetail(genderset);
	        adapter1.notifyDataSetChanged();
        }else{
        	 gender.setDetail("��");
 	        adapter1.notifyDataSetChanged();
        }
        
        String file_name = Environment.getExternalStorageDirectory().toString()+"/"+"save.png";
        Bitmap bitmap = BitmapFactory.decodeFile(file_name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] info = baos.toByteArray();
        if(bitmap.getHeight()!=0){
        	Drawable drawable = new BitmapDrawable(bitmap);
    		iconImage.setImageDrawable(drawable);
        }
        
        /*���ͷ�����õ�һЩ�����������Ի���ĵ������������յ�ѡ��*/
		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Me_Persion_Setting.this);
				builder.setTitle("ͷ������")
				.setItems(Iconitems, new DialogInterface.OnClickListener() {
                    @Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							Intent intentFromGallery = new Intent();
							intentFromGallery.setType("image/*"); // �����ļ�����
							intentFromGallery
									.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intentFromGallery,
									IMAGE_REQUEST_CODE);
							break;
						case 1:
							Intent intentFromCapture = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							// �жϴ洢���Ƿ�����ã����ý��д洢
							if (Tools.hasSdcard()) {

								intentFromCapture.putExtra(
										MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(new File(Environment
												.getExternalStorageDirectory(),
												"daniel.jpg")));
							}

							startActivityForResult(intentFromCapture,
									CAMERA_REQUEST_CODE);
							break;
						}
					}
					
				})
				.setNegativeButton("ȡ��", null)
				.show();
			}
		};
		iconSet.setOnClickListener(listener); //�������ͷ��
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Item1 itemnum = itemList1.get(position);
				LayoutInflater layoutInflater = LayoutInflater.from(Me_Persion_Setting.this);
                View dialog = layoutInflater.inflate(R.layout.me_dialog, null);
                final EditText dia = (EditText)dialog.findViewById(R.id.dialog);
                
				AlertDialog.Builder builder = new AlertDialog.Builder(Me_Persion_Setting.this);
				if(itemnum.getInfo()=="�ǳ�")
				{
				    builder.setTitle("���������ǳ�")
					.setPositiveButton("ȷ��",new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String val="";
				     		val = dia.getText().toString();
				     		if(!val.isEmpty()){
								nickname.setDetail(val);
								adapter1.notifyDataSetChanged();
								editor.putString("nickname", val);
								editor.commit();	
				     		}
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
							String val="";
				     		val = dia.getText().toString();
				     		if(!val.isEmpty()){
			     			school.setDetail(val);
			     		    adapter1.notifyDataSetChanged();
							editor.putString("school", val);
							editor.commit();
						    }
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
					    if(which==0){
					    editor.putString("gender", "��");
					    editor.commit();
					    }else{
					    editor.putString("gender", "Ů");
						editor.commit();
					    }
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
	 
	
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_CANCELED){
			switch (requestCode){
			case IMAGE_REQUEST_CODE:
				startPhotoZoom(data.getData());
				break;
			case CAMERA_REQUEST_CODE:
				if (Tools.hasSdcard()) {
					File tempFile = new File(
							Environment.getExternalStorageDirectory()
									+ "/daniel.jpg");
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					Toast.makeText(getApplicationContext(), "δ�ҵ��洢�����޷��洢��Ƭ��",
							Toast.LENGTH_LONG).show();
				}
               break;	
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void startPhotoZoom(Uri uri) {
		// TODO Auto-generated method stub
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// ���òü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}	
	
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			iconImage.setImageDrawable(drawable);
			if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			{
			//	Toast.makeText(Me_Persion_Setting.this, "123", Toast.LENGTH_SHORT).show();
				File file = new File(Environment.getExternalStorageDirectory(),"save.png");
				try {
					file.createNewFile();
					FileOutputStream out = new FileOutputStream(file); 
					photo.compress(Bitmap.CompressFormat.PNG, 100, out); 
					out.flush(); 
					out.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	} 
	 private void initItem() {
		
			nickname = new Item1("�ǳ�","�������");
			itemList1.add(nickname);
		    gender = new Item1("�Ա�","��");
			itemList1.add(gender);
			school = new Item1("ѧУ","��������ѧ");
			itemList1.add(school);
		}
}
