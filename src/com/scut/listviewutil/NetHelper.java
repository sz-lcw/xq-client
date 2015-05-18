package com.scut.listviewutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**�ж����縨����
 * @author szlcw
 *
 */
public class NetHelper {

    /**�ж������Ƿ����
     * @param context
     * @return bollean  ������true,������false
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
    
    
    /**�ж��Ƿ���wifi����
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
    /**�ж��Ƿ���3G����
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
