package libcore.io;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.http.HttpStatus;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
 
public class Utils {
 
    /**
     * ��ȡDiskLruCache�Ļ����ļ���
     * ע��ڶ�������dataType
     * DiskLruCache��һ��String���͵�Ψһֵ�Բ�ͬ���͵����ݽ�������.
     * ����bitmap,object���ļ���.������bitmap�ļ����л�����ͼƬ.
     * 
     * �������ݵĴ��λ��Ϊ:
     * /sdcard/Android/data//cache 
     * ���SD��������ʱ������λ��Ϊ:
     * /data/data//cache
     * 
     */
    public static File getDiskLruCacheDir(Context context, String dataType) {
        String dirPath;
        File cacheFile = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
            !Environment.isExternalStorageRemovable()) {
            dirPath=context.getExternalCacheDir().getPath();
        } else {
            dirPath=context.getCacheDir().getPath();
        }
        cacheFile=new File(dirPath+File.separator+dataType);
        return cacheFile;
    }
     
     
     
    /**
     * ��ȡAPP��ǰ�汾��
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context){
        int versionCode=1;
        try {
            String packageName=context.getPackageName();
            PackageManager packageManager=context.getPackageManager();
            PackageInfo packageInfo=packageManager.getPackageInfo(packageName, 0);
            versionCode=packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
     
     
    /**
     * ���ַ�����MD5����.
     * �����ڸ�ʾ���н�url����MD5����
     */
    public static String getStringByMD5(String string) {
        String md5String = null;
        try {
            // Create MD5 Hash
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte messageDigestByteArray[] = messageDigest.digest();
            if (messageDigestByteArray == null || messageDigestByteArray.length == 0) {
                return md5String;
            }
 
            // Create hexadecimal String
            StringBuffer hexadecimalStringBuffer = new StringBuffer();
            int length = messageDigestByteArray.length;
            for (int i = 0; i < length; i++) {
                hexadecimalStringBuffer.append(Integer.toHexString(0xFF & messageDigestByteArray[i]));
            }
            md5String = hexadecimalStringBuffer.toString();
            return md5String;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5String;
    }
     
     
    /**
     * �������ȡͼƬ�ұ�����SD���еĻ���
     */
    public static boolean getBitmapFromNetWorkAndSaveToDiskLruCache(String imageUrl,OutputStream outputStream){
        boolean isSuccessfull=false;
        URL url=null;
        HttpURLConnection httpURLConnection=null;
        BufferedOutputStream bufferedOutputStream=null;
        InputStream inputStream=null;
        BufferedInputStream bufferedInputStream=null;
        try {
            url=new URL(imageUrl);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5*1000);
            httpURLConnection.setReadTimeout(10*1000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            if (httpURLConnection.getResponseCode()==HttpStatus.SC_OK) {
                bufferedOutputStream=new BufferedOutputStream(outputStream);
                inputStream=httpURLConnection.getInputStream();
                bufferedInputStream=new BufferedInputStream(inputStream);
                int len=0;
                byte [] buffer=new byte[1024];
                while((len=bufferedInputStream.read(buffer))!=-1){
                    bufferedOutputStream.write(buffer, 0, len);
                    bufferedOutputStream.flush();
                }
                isSuccessfull=true;
            } else {
                isSuccessfull=false;
                System.out.println("ͼƬ����ʧ��");
            }
        } catch (Exception e) {
                isSuccessfull=false;
                System.out.println("e="+e.toString());
        }finally{
            try {
                if (bufferedOutputStream!=null) {
                    bufferedOutputStream.close();
                }
                if (inputStream!=null) {
                    inputStream.close();
                }
                if (bufferedInputStream!=null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection!=null) {
                    httpURLConnection.disconnect();
                }
            } catch (Exception e) {
                 System.out.println("e="+e.toString());
            }
        }
         
        return isSuccessfull;
         
    }
 
}