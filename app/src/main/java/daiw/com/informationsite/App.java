package daiw.com.informationsite;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import daiw.com.core.app.ProjectInit;
import daiw.com.core.net.Constancts;
import daiw.com.informationsite.http.ApiConstants;
import daiw.com.informationsite.interf.PermissionsRequestInterf;
import daiw.com.informationsite.utils.hook.HookUtils;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/7  6:48 PM
 *
 *         ***************************
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        initRetrofit();
        //初始化Hook集中式登录
        initHookUtils();
    }
     /*
      * @Description : 初始化Hook集中式登录
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/9
      */
    private void initHookUtils(){
        HookUtils hookUtils = new HookUtils();
        hookUtils.hookSatrtActivity(this);
        hookUtils.hooKMh();
    }

    private void initRetrofit() {
        Logger.addLogAdapter(new AndroidLogAdapter());

        ArrayList<String> headerValues = new ArrayList<>();
        headerValues.add("wanandroid");
        headerValues.add("juhe");

        ProjectInit.init(this)
                .withApiHost(ApiConstants.URL_HEADER_VALUE_1)
                .withHeaderValues(ApiConstants.URL_HEADER_KEY_1,ApiConstants.URL_HEADER_VALUE_1)
                .withHeaderValues(ApiConstants.URL_HEADER_KEY_2,ApiConstants.URL_HEADER_VALUE_2)
                .configure();
    }

    public static Context getContext() {
        return context;
    }
}
