package com.scut.listviewutil;  
   
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import libcore.io.DiskLruCache;
import libcore.io.Utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;  
   









import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;  
   
/** ͼ�񻺴���
 * 
 *
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements  
        ImageCache {
    final static int DISK_MAX_SIZE  = (int) (Runtime.getRuntime().maxMemory() / 1024);  
    final static int RAM_CACHE_SIZE  = DISK_MAX_SIZE / 8;
    String DISK_CACHE_DIR = "image"; 
    DiskLruCache diskLruCache;  
    public static int getDefaultLruCacheSize() {  
        return RAM_CACHE_SIZE;  
    }  
   
    public LruBitmapCache() {  
        this(getDefaultLruCacheSize());
    }  
   
    public LruBitmapCache(int sizeInKiloBytes) {  
        super(sizeInKiloBytes);
        File cacheDir = new File(Environment.getExternalStorageDirectory(), DISK_CACHE_DIR);  
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
   
    @Override  
    protected int sizeOf(String key, Bitmap value) {  
        return value.getRowBytes() * value.getHeight() / 1024;  
    }  
   
    @Override  
    public Bitmap getBitmap(String url) {  
        //return get(url); 
        String key=generateKey(url);  
        Bitmap bmp = get(key);  
        if (bmp == null) {  
            bmp = getBitmapFromDiskLruCache(key);  //��ͼ��ӻ�����ȡ��
            //�Ӵ��̶����󣬷����ڴ�  
            if(bmp!=null) {
                put(key,bmp);
            }
        }
        return bmp;     	
    }  
   
    @Override  
    public void putBitmap(String url, Bitmap bitmap) {  
        //put(url, bitmap); 
        String key=generateKey(url);  
        //put(url, bitmap);  
        put(key,bitmap);
        putBitmapToDiskLruCache(key,bitmap);     	
    }
    
    /**��ͼ�񱣴浽����
     * @param key		ͼ��URL��Ӧ��MD5��
     * @param bitmap	ͼ��
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
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }    
    /**��ͼ��ӻ�����ȡ��
     * @param key		ͼ��URL��Ӧ��MD5��
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
     * ��ΪDiskLruCache��key�����ƣ�ֻ����[a-z0-9_-]{1,64},������md5����key 
     * @param url 
     * @return 
     */  
    private String generateKey(String url)  
    {  
        return Utils.getStringByMD5(url);  
    }    
}  