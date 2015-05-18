package com.scut.listviewutil;  
   

import android.app.Application;  
import android.text.TextUtils;  
   
import com.android.volley.Request;  
import com.android.volley.RequestQueue;  
import com.android.volley.toolbox.ImageLoader;  
import com.android.volley.toolbox.Volley;  

   
/**图像加载控制类
 * @author 
 *	使各文件中获取同一（单一）实例AppController
 */
public class AppController extends Application {  
   
    public static final String TAG = AppController.class.getSimpleName();  
   
    private RequestQueue mRequestQueue;  //RequestQueue是一个请求队列对象，
    									//它可以缓存所有的HTTP请求，然后按照一定的算法并发地发出这些请求
    private ImageLoader mImageLoader;  	//加载网络图片的封装类
   
    private static AppController mInstance;  
   
    @Override  
    public void onCreate() {  
        super.onCreate();  
        mInstance = this;  
    }  
   
    public static synchronized AppController getInstance() {  
        return mInstance;  
    }  
   
    public RequestQueue getRequestQueue() {  
        if (mRequestQueue == null) {  
        	
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());  
        }  
   
        return mRequestQueue;  
    }  
   
    public ImageLoader getImageLoader() {  
        getRequestQueue();  
        if (mImageLoader == null) {  
            mImageLoader = new ImageLoader(this.mRequestQueue,  
                    new LruBitmapCache(mInstance));  //创建并配置图像缓存
        }  
        return this.mImageLoader;  
    }  
   
    public <T> void addToRequestQueue(Request<T> req, String tag) {  
        // set the default tag if tag is empty  
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);  
        getRequestQueue().add(req);  
    }  
   
    public <T> void addToRequestQueue(Request<T> req) {  
        req.setTag(TAG);  
        getRequestQueue().add(req);  
    }  
   
    public void cancelPendingRequests(Object tag) {  
        if (mRequestQueue != null) {  
            mRequestQueue.cancelAll(tag);  
        }  
    }  
}  