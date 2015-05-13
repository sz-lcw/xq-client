package com.scut.listviewutil;  
   
 

import itat.zttc.login.R;
import java.util.List;  
import android.app.Activity;  
import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.TextView;  
   
import com.android.volley.toolbox.ImageLoader;  
import com.android.volley.toolbox.NetworkImageView;  
import com.scut.model.HeadInformation;

   
public class CustomListAdapter extends BaseAdapter {  
    private Activity activity;  
    private LayoutInflater inflater;  
    private List<HeadInformation> infoItemList;  //存放信息头的list
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();//?  
   
    public CustomListAdapter(Activity activity, List<HeadInformation> infoItemList) {  
        this.activity = activity;  
        this.infoItemList = infoItemList;  
    }  
   
    @Override  
    public int getCount() {  
        return infoItemList.size();  
    }  
   
    @Override  
    public Object getItem(int location) {  
        return infoItemList.get(location);  
    }  
   
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
   
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
    	ViewHolder viewHolder;//用于对控件的实例进行缓存
        if (inflater == null)  
            inflater = (LayoutInflater) activity  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        if (convertView == null){  
            convertView = inflater.inflate(R.layout.list_row, null);  //加载item子项的布局
            viewHolder = new ViewHolder();
            viewHolder.img = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);  
            viewHolder.host = (TextView) convertView.findViewById(R.id.rating);
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.contentId); 
            convertView.setTag(viewHolder);		//将ViewHolder存储在View中
        } else{
        	viewHolder = (ViewHolder) convertView.getTag();//重新获取ViewHolder
        }
        if (imageLoader == null)  
            imageLoader = AppController.getInstance().getImageLoader();  
/*        NetworkImageView img = (NetworkImageView) convertView  
                .findViewById(R.id.thumbnail);  
        TextView title = (TextView) convertView.findViewById(R.id.title);  
        TextView host = (TextView) convertView.findViewById(R.id.rating);  
        TextView genre = (TextView) convertView.findViewById(R.id.genre);  
        TextView idTextView = (TextView) convertView.findViewById(R.id.contentId);  */
        
        // getting movie data for the row  
        HeadInformation headInfo = infoItemList.get(position);  
   
        // thumbnail image  
        viewHolder.img.setImageUrl(headInfo.getImgUrl(), imageLoader);  
        
        // title  
        viewHolder.title.setText(headInfo.getTitle());  
           
        // host  
        viewHolder.host.setText("Host:" + String.valueOf(headInfo.getHost()));  
           
        
           
        // release year  
		viewHolder.idTextView.setText(String.valueOf(headInfo.getId()));  
		viewHolder.idTextView.setVisibility(View.GONE);     
        return convertView;  
    }  
   
    /**内部类，用于对控件的实例进行缓存
     * @author szlcw
     *
     */
    class ViewHolder{
    	NetworkImageView img;
    	TextView title;
    	TextView host;
    	TextView idTextView;
    }
}  