package daiw.com.informationsite.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/7  11:42 PM
 *
 *         ***************************
 */
public class AppMessage {

     /*
      * @Description : 获取版本名
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

     /*
      * @Description :获取版本号
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }
     /*
      * @Description :通过PackageInfo得到的想要启动的应用的包名
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pInfo = null;

        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = context.getPackageManager();
            pInfo = pManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pInfo;
    }

}
