package daiw.com.informationsite.manager;

import android.content.Context;
import android.content.Intent;

import daiw.com.informationsite.MainActivity;
import daiw.com.informationsite.view.login.LoginActivity;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/5  4:43 PM
 *
 *         ***************************
 */
public class StartActivityManager {
     /*
      * @Description :跳转首页
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/5
      */
    public static void startManiActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
     /*
      * @Description : 跳转登录页面
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/22
      */
    public static void startLoginActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
