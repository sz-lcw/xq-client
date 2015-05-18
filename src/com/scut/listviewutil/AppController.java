package com.scut.listviewutil;  
   

import android.app.Application;  
import android.text.TextUtils;  
   
import com.android.volley.Request;  
import com.android.volley.RequestQueue;  
import com.android.volley.toolbox.ImageLoader;  
import com.android.volley.toolbox.Volley;  

   
/**ͼ����ؿ�����
 * @author 
 *	ʹ���ļ��л�ȡͬһ����һ��ʵ��AppController
 */
public class AppController extends Application {  
   
    public static final String TAG = AppController.class.getSimpleName();  
   
    private RequestQueue mRequestQueue;  //RequestQueue��һ��������ж���
    									//�����Ի������е�HTTP����Ȼ����һ�����㷨�����ط�����Щ����
    private ImageLoader mImageLoader;  	//��������ͼƬ�ķ�װ��
   
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
                    new LruBitmapCache(mInstance));  //����������ͼ�񻺴�
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