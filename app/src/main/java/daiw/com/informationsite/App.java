package daiw.com.informationsite;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

import daiw.com.core.app.ProjectInit;
import daiw.com.core.net.Constancts;
import daiw.com.informationsite.http.ApiConstants;

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
    }

    private void initRetrofit() {
        ArrayList<String> headerValues = new ArrayList<>();
        headerValues.add("wanandroid");
        headerValues.add("juhe");

        ProjectInit.init(this)
                .withApiHost("http://www.wanandroid.com/")
                .withHeaderValues(ApiConstants.URL_HEADER_KEY_1,ApiConstants.URL_HEADER_VALUE_1)
                .withHeaderValues(ApiConstants.URL_HEADER_KEY_2,ApiConstants.URL_HEADER_VALUE_2)
                .configure();
    }

    public static Context getContext() {
        return context;
    }
}
