package com.scut.listviewutil;  
   
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import libcore.io.DiskLruCache;
import libcore.io.Utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;  
   










import android.content.Context;
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;  
   
/** 图像缓存类
 *  增加了DiskLruCache，将数据缓存到SD卡中
 *
 */
public class LruBitmapCache implements  
        ImageCache {
    final static int DISK_MAX_SIZE  = 20 * 1024 * 1024;    
    final static int RAM_CACHE_SIZE  = 5 * 1024 * 1024;  
    String DISK_CACHE_DIR = "image"; 
    DiskLruCache diskLruCache;  
    private Context mContext;
    private LruCache<String, Bitmap>  lruCache;
    
    public static int getDefaultLruCacheSize() {  
        return RAM_CACHE_SIZE;  
    }  
   
    public LruBitmapCache(Context context) {  
    	//this(getDefaultLruCacheSize());
    	mContext = context;    	
    	this.lruCache = new LruCache<String, Bitmap>(DISK_MAX_SIZE) {  
            @Override  
            protected int sizeOf(String key, Bitmap value) {  
            	return value.getRowBytes() * value.getHeight();  
            }  
        };
        //File cacheDir = new File(Environment.getExternalStorageDirectory(), DISK_CACHE_DIR);
        File cacheDir = Utils.getDiskLruCacheDir(mContext, DISK_CACHE_DIR); 
        if(!cacheDir.exists())  
        {  
            cacheDir.mkdir();  
        }  
        try {  
            diskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_MAX_SIZE);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }         
    }  
   
   
/*    @Override  
    protected int sizeOf(String key, Bitmap value) {  
        return value.getRowBytes() * value.getHeight() / 1024;  
    }  */
   
    @Override  
    public Bitmap getBitmap(String url) {  
        //return get(url); 
        String key=generateKey(url);  
        Bitmap bmp = lruCache.get(key);  
        if (bmp == null) {  
            bmp = getBitmapFromDiskLruCache(key);  //将图像从缓存中取出
            //从磁盘读出后，放入内存  
            if(bmp!=null) {
            	lruCache.put(key,bmp);
            }
        }
        return bmp;     	
    }  
   
    @Override  
    public void putBitmap(String url, Bitmap bitmap) {  
        //put(url, bitmap); 
        String key=generateKey(url);  
        //put(url, bitmap);  
        lruCache.put(key,bitmap);
        putBitmapToDiskLruCache(key,bitmap);     	
    }
    
    /**将图像保存到缓存
     * @param key		图像URL对应的MD5码
     * @param bitmap	图像
     */
    private void putBitmapToDiskLruCache(String key, Bitmap bitmap) {  
        try {  
            DiskLruCache.Editor editor = diskLruCache.edit(key);  
            if(editor!=null)  
            {  
                OutputStream outputStream = editor.newOutputStream(0);  
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);  
                editor.commit();  
            }
            diskLruCache.flush();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }    
    /**将图像从缓存中取出
     * @param key		图像URL对应的MD5码
     * @return
     */
    private Bitmap getBitmapFromDiskLruCache(String key) {  
        try {  
            DiskLruCache.Snapshot snapshot=diskLruCache.get(key);  
            if(snapshot!=null)  
            {  
                InputStream inputStream = snapshot.getInputStream(0);  
                if (inputStream != null) {  
                    Bitmap bmp = BitmapFactory.decodeStream(inputStream);  
                    inputStream.close();  
                    return bmp;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
   
    /** 
     * 因为DiskLruCache对key有限制，只能是[a-z0-9_-]{1,64},所以用md5生成key 
     * @param url 
     * @return 
     */  
    private String generateKey(String url)  
    {  
        return Utils.getStringByMD5(url);  
    }    
}  