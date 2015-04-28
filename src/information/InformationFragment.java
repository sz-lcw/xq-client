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
	//��������LinearLayout
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
	        
	        //���Ҳ����ļ���LayoutInflater.inflate
		    LayoutInflater inflater2=getLayoutInflater(savedInstanceState);
	     
	        View view1 = inflater2.inflate(R.layout.information_slide_picture1, null);
	        View view2 = inflater2.inflate(R.layout.information_slide_picture2, null);
	        View view3 = inflater2.inflate(R.layout.information_slide_picture3, null);
	        
	        //��viewװ������
	        pageview =new ArrayList<View>();
	        pageview.add(view1);
	        pageview.add(view2);
	        pageview.add(view3);
	               
	                
	        group = (ViewGroup)view.findViewById(R.id.viewGroup); 
	        
	        //�ж�����ͼ���ж��ٸ����
	        imageViews = new ImageView[pageview.size()];
	        for(int i =0;i<pageview.size();i++){
	        	imageView = new ImageView(this.getActivity());
	        	imageView.setLayoutParams(new LayoutParams(20,20));
	        	imageView.setPadding(20, 0, 20, 0); 
	        	imageViews[i] = imageView;   
	        	
	        	//Ĭ�ϵ�һ��ͼ��ʾΪѡ��״̬
	        	if (i == 0) {  
	                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);  
	            } else {  
	                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
	            }  
	            
	            group.addView(imageViews[i]);  
	        }
	                             

	        
	        //��������
	        viewPager.setAdapter(mPagerAdapter);
	        //�󶨼����¼�
	        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	        
	       
	        
	        
	          listView=(ListView)view.findViewById(R.id.info_tab1);
	          String[] title=new String[]{"��Ѷ�����������Ƹ","�ൺ�Ƽ���ѧ��Ƹ","��������ѧ��Ƹ","��Ѷ�����������Ƹ","�ൺ�Ƽ���ѧ��Ƹ","��������ѧ��Ƹ"};
	          String[] address=new String[]{"3��¥�Զ���ѧԺ5¥������","27��¥����ѧԺ270101��","34��¥340201����","3��¥�Զ���ѧԺ5¥������","27��¥����ѧԺ270101��","34��¥340201����"};
	          String[] time=new String[]{"12.14��12.19","12.14��12.19","12.14��12.19","12.14��12.19","12.14��12.19","12.14��12.19"};
	          String[] deparment=new String[]{"��Ѷ����","�ൺ�Ƽ���ѧ","��������ѧ","��Ѷ����","�ൺ�Ƽ���ѧ","��������ѧ"};
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
	        		intent.putExtra("a", "���ǵ�"+position+"������");
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
	        		intent.putExtra("a", "���ǵ�"+position+"������");
	        		intent.setClass(getActivity(), Show.class);
	        		startActivity(intent);
                }
	        
	        });
			
			
		 
	        return view;
	        
	        
	        
	        
		
	}
	/*
	 PagerAdapter mPagerAdapter = new PagerAdapter(){

			@Override
			//��ȡ��ǰ���������
			public int getCount() {
				// TODO Auto-generated method stub
				return pageview.size();
			}

			@Override
			//���Ƿ��ɶ������ɽ���
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			//�Ǵ�ViewGroup���Ƴ���ǰView
			 public void destroyItem(View arg0, int arg1, Object arg2) {  
		            ((ViewPager) arg0).removeView(pageview.get(arg1));  
		        }  
			
			//����һ������������������PagerAdapter������ѡ���ĸ�������ڵ�ǰ��ViewPager��
			public Object instantiateItem(View arg0, int arg1){
				((ViewPager)arg0).addView(pageview.get(arg1));
				return pageview.get(arg1);				
			}
			
	    	
	    };
	    
	    //pageView������
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
			//����л��ˣ��Ͱѵ�ǰ�ĵ������Ϊѡ�б�������������δѡ�б���
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
		mListView.setRefreshTime("刚刚");
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
