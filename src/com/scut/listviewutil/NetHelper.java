package com.scut.listviewutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**判断网络辅助类
 * @author szlcw
 *
 */
public class NetHelper {

    /**判断网络是否可用
     * @param context
     * @return bollean  联网则true,断网则false
     */
	public static boolean isNetworkAvailable(Context context) {   
		try {
			ConnectivityManager manger = (ConnectivityManager) context
	                .getSystemService(Context.CONNECTIVITY_SERVICE); 
	        NetworkInfo info = manger.getActiveNetworkInfo();
	        //return (info!=null && info.isConnected());//
	        if(info != null){
	        	return info.isConnected();
	        }else{
	        	return false;
	        }
		} catch (Exception e) {
	        return false;
		}
	}
    
    
    /**判断是否是wifi连接
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {   
            return true;   
        }   
        return false;   
    }
    /**判断是否是3G网络
     * @param context
     * @return
     */
    public static boolean is3g(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
            return true;   
        }   
        return false;   
    }      
}
