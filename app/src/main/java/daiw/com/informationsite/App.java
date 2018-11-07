package daiw.com.informationsite;

import android.app.Application;
import android.content.Context;

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

    }

    public static Context getContext() {
        return context;
    }
}
