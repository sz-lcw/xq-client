package com.scut.information;


import itat.zttc.login.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Cache.Entry;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.scut.listviewutil.AppController;
import com.scut.listviewutil.CustomListAdapter;
import com.scut.listviewutil.NetHelper;
import com.scut.listviewutil.XListView;
import com.scut.listviewutil.XListView.IXListViewListener;
import com.scut.model.HeadInformation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class InformationFragment extends Fragment implements IXListViewListener {

	 // Log tag  
	 private static final String TAG =InformationFragment.class.getSimpleName();  

	 // Movies json url  
	 private static final String url = "http://192.168.1.114:8080/xq/information_list.action";  
	 private ProgressDialog pDialog;  
	 private List<HeadInformation> headInformationsList = new ArrayList<HeadInformation>();  //�����Ϣͷ��list
	 private ListView listView;  
	 private CustomListAdapter adapter;  

	private XListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	
	 private static final int ERROR_RESPONSE = 1;	//
	 
	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View viewMain = inflater.inflate(R.layout.information, container,
				false);

		
		
		
		 geneItems();
		 mListView = (XListView) viewMain.findViewById(R.id.xListView);  
		 mListView.setPullLoadEnable(true);
	     adapter = new CustomListAdapter(getActivity(), headInformationsList);  
	     mListView.setAdapter(adapter);  
		
		
		
		/*geneItems();
		mListView = (XListView) view.findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);*/
	/*	mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item,
				items);*/
		
		/*mListView.setAdapter(mAdapter);*/
		// mListView.setPullLoadEnable(false);
		// mListView.setPullRefreshEnable(false);
		mListView.setXListViewListener(this);
		mHandler = new Handler();

		
		 mListView.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
             public void onItemClick(AdapterView<?> parent, View view,
                             int position, long id) {
	        		TextView idTextView=(TextView) view.findViewById(R.id.title);
	        		TextView hostTextView=(TextView) view.findViewById(R.id.rating);
	        		/*TextView titleTextView=(TextView) view.findViewById(R.id.title);*/
	        		
	        		
	        		Intent intent=new Intent();
	        		intent.putExtra("id", idTextView.getText().toString());
	        		intent.putExtra("host", hostTextView.getText().toString());
	        		intent.setClass(getActivity(), Show.class);
	        		startActivity(intent);
             }
	        
	        });

		  pDialog = new ProgressDialog(getActivity());  
		     // Showing progress dialog before making http request  
		     pDialog.setMessage("Loading...");  
		     pDialog.show();  

		     // changing action bar color  
		     /*getActionBar().setBackgroundDrawable(  
		             new ColorDrawable(Color.parseColor("#1b1b1b")));  */

		     // Creating volley request obj
		     Context context = getActivity().getApplicationContext();
		     if(!NetHelper.isNetworkAvailable(context)){
		    	 getFromDiskCache(url);		    	 //���û�������ȡ��������
		    }else{
		    	//����������ϸ�������				 
			     JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url,		//JsonArrayRequest�ɰѽ��������JSONArray
			             new Response.Listener<JSONArray>() {  
			                 @Override  
			                 public void onResponse(JSONArray response) {  
			                     Log.d(TAG, response.toString());  
			                     hidePDialog();  	
			                     // Parsing json  
			                     for (int i = 0; i < response.length(); i++) {  
			                         try {  
	
			                             JSONObject obj = response.getJSONObject(i);  
			                             HeadInformation head = new HeadInformation();  
			                             head.setTitle(obj.getString("title"));  
			                             head.setImgUrl(obj.getString("headImage"));  
			                             head.setHost(obj.getString("host"));
			                             head.setId(obj.getInt("id"));
	
			                             // adding movie to movies array  
			                             headInformationsList.add(head);  
	
			                         } catch (JSONException e) {  
			                             e.printStackTrace();  
			                         }  
	
			                     }  
	
			                     // notifying list adapter about data changes  
			                     // so that it renders the list view with updated data  
			                     adapter.notifyDataSetChanged();  
			                 }  
			             }, new Response.ErrorListener() {  
			                 @Override  
			                 public void onErrorResponse(VolleyError error) {  
			                     VolleyLog.d(TAG, "Error: " + error.getMessage());
			                     getFromDiskCache(url);//��Ӧʧ�����ȡ����
								adapter.notifyDataSetChanged();//ˢ��UI
			                     hidePDialog();
	
			                 }  
			             });    
					// Adding request to request queue  
					AppController.getInstance().addToRequestQueue(jsonArrayReq);  
		    	}
		
		     
		
		     
		return viewMain;

	}

	/**�Ӵ����л�ȡ��������
	 * @param url	
	 */
	private void getFromDiskCache(String url) {
		if(AppController.getInstance().getRequestQueue().getCache().get(url)!=null){
			try {
				String str = new String(AppController.getInstance().getRequestQueue().getCache().get(url).data);
				JSONArray response = new JSONArray(str);
				Log.d(TAG, response.toString());  
			    hidePDialog(); 
			for (int i = 0; i < response.length(); i++) {  
			         JSONObject obj = response.getJSONObject(i);  
			         HeadInformation head = new HeadInformation();  
			         head.setTitle(obj.getString("title"));  
			         head.setImgUrl(obj.getString("headImage"));  
			         head.setHost(obj.getString("host"));
			         head.setId(obj.getInt("id"));
			
			         // adding movie to movies array  
			         headInformationsList.add(head);  
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Log.d(TAG, "û�л�������");  
		}
	}

	private void geneItems() {
		/*for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}*/
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("�ո�");
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				/*mAdapter = new ArrayAdapter<String>(getActivity(),
						R.layout.list_item, items);
				mListView.setAdapter(mAdapter);*/
				 adapter = new CustomListAdapter(getActivity(), headInformationsList);  
			     mListView.setAdapter(adapter);  
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				adapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);

	}
	
	
	 @Override  
	 public void onDestroy() {  
	     super.onDestroy();  
	     hidePDialog();  
	 }  
	 
	 /**���ؼ��ضԻ���
	 * 
	 */
	private void hidePDialog() {  
	     if (pDialog != null) {  
	         pDialog.dismiss();  
	         pDialog = null;  
	     }  
	 }  


}
