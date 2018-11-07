package daiw.com.informationsite.utils.log;

import android.util.Log;

import daiw.com.informationsite.api.constans.Constans;

/****************************
 * 类描述：日志管理类
 *
 * @author: daiw
 * @time: 2018/11/7  8:09 PM
 *
 *         ***************************
 */
public class LogoutUtils {
    private static final String TAG = LogoutUtils.class.getCanonicalName();

    public static void i(String message){
        if(Constans.DEBUG){
            Log.i(TAG,message);
        }
    }
    public static void i(String tag,String message){
        if(Constans.DEBUG){
            Log.i(tag,message);
        }
    }
    public static void d(String message){
        if(Constans.DEBUG){
            Log.d(TAG,message);
        }
    }
    public static void d(String tag,String message){
        if(Constans.DEBUG){
            Log.d(tag,message);
        }
    }
    public static void e(String message){
        if(Constans.DEBUG){
            Log.e(TAG,message);
        }
    }
    public static void e(String tag,String message){
        if(Constans.DEBUG){
            Log.e(tag,message);
        }
    }
    public static void w(String message){
        if(Constans.DEBUG){
            Log.w(TAG,message);
        }
    }
    public static void w(String tag,String message){
        if(Constans.DEBUG){
            Log.w(tag,message);
        }
    }
}
