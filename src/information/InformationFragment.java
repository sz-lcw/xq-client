package information;

import itat.zttc.login.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class InformationFragment extends Fragment implements IXListViewListener
{
	/*private ViewPager viewPager;
	private ArrayList<View> pageview;
	private ImageView imageView; 
	private ImageView[] imageViews; 
	//包裹点点的LinearLayout
	private ViewGroup group;
	
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	private SimpleAdapter simpleAdapter;*/
	private XListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	/** Called when the activity is first created. */
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		 final   View view=inflater.inflate(R.layout.information, container, false);
		
		  /*viewPager = (ViewPager) view.findViewById(R.id.viewPager);
	        
	        //查找布局文件用LayoutInflater.inflate
		    LayoutInflater inflater2=getLayoutInflater(savedInstanceState);
	     
	        View view1 = inflater2.inflate(R.layout.information_slide_picture1, null);
	        View view2 = inflater2.inflate(R.layout.information_slide_picture2, null);
	        View view3 = inflater2.inflate(R.layout.information_slide_picture3, null);
	        
	        //将view装入数组
	        pageview =new ArrayList<View>();
	        pageview.add(view1);
	        pageview.add(view2);
	        pageview.add(view3);
	               
	                
	        group = (ViewGroup)view.findViewById(R.id.viewGroup); 
	        
	        //有多少张图就有多少个点点
	        imageViews = new ImageView[pageview.size()];
	        for(int i =0;i<pageview.size();i++){
	        	imageView = new ImageView(this.getActivity());
	        	imageView.setLayoutParams(new LayoutParams(20,20));
	        	imageView.setPadding(20, 0, 20, 0); 
	        	imageViews[i] = imageView;   
	        	
	        	//默认第一张图显示为选中状态
	        	if (i == 0) {  
	                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);  
	            } else {  
	                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
	            }  
	            
	            group.addView(imageViews[i]);  
	        }
	                             

	        
	        //绑定适配器
	        viewPager.setAdapter(mPagerAdapter);
	        //绑定监听事件
	        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	        
	       
	        
	        
	          listView=(ListView)view.findViewById(R.id.info_tab1);
	          String[] title=new String[]{"腾讯计算机集团招聘","青岛科技大学招聘","华南理工大学招聘","腾讯计算机集团招聘","青岛科技大学招聘","华南理工大学招聘"};
	          String[] address=new String[]{"3号楼自动化学院5楼会议室","27号楼建筑学院270101室","34号楼340201教室","3号楼自动化学院5楼会议室","27号楼建筑学院270101室","34号楼340201教室"};
	          String[] time=new String[]{"12.14至12.19","12.14至12.19","12.14至12.19","12.14至12.19","12.14至12.19","12.14至12.19"};
	          String[] deparment=new String[]{"腾讯集团","青岛科技大学","华南理工大学","腾讯集团","青岛科技大学","华南理工大学"};
	          int[] image=new int[]{R.drawable.lo,R.drawable.lo,R.drawable.lo,R.drawable.lo,R.drawable.lo,R.drawable.lo};
	          List<Map<String, Object>> listitems=new ArrayList<Map<String,Object>>();
	          for(int i=0;i<title.length;i++)
	          {
	        	  Map<String, Object> map=new HashMap<String, Object>();
	        	  map.put("title", title[i]);
	        	  map.put("image", image[i]);
	        	  map.put("address",address[i]);
	        	  map.put("deparment", deparment[i]);
	        	  map.put("time", time[i]);
	        	  listitems.add(map);  
	          }
	         
          simpleAdapter=new SimpleAdapter(this.getActivity(),listitems,
  		R.layout.msg_list, 
  		new String[] {"title","image","time","deparment","address"},new int[]{R.id.msg_list_title,
          	R.id.msg_list_image,R.id.msg_list_time,R.id.msg_list_deparment,R.id.msg_list_address});
          System.out.println("mem");
          listView.setAdapter(simpleAdapter);
	        
	        listView.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
                public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
	        		Intent intent=new Intent();
	        		intent.putExtra("a", "这是第"+position+"的内容");
	        		intent.setClass(getActivity(), Show.class);
	        		startActivity(intent);
                }
	        
	        });
	        
          
	        */
		 geneItems();
			mListView = (XListView) view.findViewById(R.id.xListView);
			mListView.setPullLoadEnable(true);
			mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, items);
			mListView.setAdapter(mAdapter);
//			mListView.setPullLoadEnable(false);
//			mListView.setPullRefreshEnable(false);
			mListView.setXListViewListener(this);
			mHandler = new Handler();
			
			
			
			mListView.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
                public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
	        		Intent intent=new Intent();
	        		intent.putExtra("a", "这是第"+position+"的内容");
	        		intent.setClass(getActivity(), Show.class);
	        		startActivity(intent);
                }
	        
	        });
			
			
		 
	        return view;
	        
	        
	        
	        
		
	}
	/*
	 PagerAdapter mPagerAdapter = new PagerAdapter(){

			@Override
			//获取当前窗体界面数
			public int getCount() {
				// TODO Auto-generated method stub
				return pageview.size();
			}

			@Override
			//断是否由对象生成界面
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			//是从ViewGroup中移出当前View
			 public void destroyItem(View arg0, int arg1, Object arg2) {  
		            ((ViewPager) arg0).removeView(pageview.get(arg1));  
		        }  
			
			//返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
			public Object instantiateItem(View arg0, int arg1){
				((ViewPager)arg0).addView(pageview.get(arg1));
				return pageview.get(arg1);				
			}
			
	    	
	    };
	    
	    //pageView监听器
	    class GuidePageChangeListener implements OnPageChangeListener{

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			//如果切换了，就把当前的点点设置为选中背景，其他设置未选中背景
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for(int i=0;i<imageViews.length;i++){
					imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
					 if (arg0 != i) {  
		                    imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
		                }  
				}
				
			}
	    	
	    }*/
	
	
	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("鍒氬垰");
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
				mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, items);
				mListView.setAdapter(mAdapter);
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
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
		
	}
}
