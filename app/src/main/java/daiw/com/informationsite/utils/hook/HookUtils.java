package daiw.com.informationsite.utils.hook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import daiw.com.informationsite.api.constans.ConfigConstans;
import daiw.com.informationsite.utils.log.LogoutUtils;
import daiw.com.informationsite.utils.sharedata.ShareData;
import daiw.com.informationsite.view.ProxyActivity;
import daiw.com.informationsite.view.login.LoginActivity;

/****************************Ø
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/12/9  3:14 PM
 *
 *         ***************************
 */
public class HookUtils {

    private Context context;

    public void hooKMh() {

        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            //还原系统的ActivityTread   mH
            Object activityThreadObject = sCurrentActivityThreadField.get(null);

            Field handlerField = activityThreadClass.getDeclaredField("mH");
            handlerField.setAccessible(true);
            //hook点找到了
            Handler mH = (Handler) handlerField.get(activityThreadObject);
            Field callbackField = Handler.class.getDeclaredField("mCallback");
            callbackField.setAccessible(true);

            callbackField.set(mH, new ActivityMH(mH));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ActivityMH implements Handler.Callback {
        private Handler mH;

        public ActivityMH(Handler mH) {
            this.mH = mH;
        }

        @Override
        public boolean handleMessage(Message msg) {
            //LAUNCH_ACTIVITY ==100 即将要加载一个activity了
            LogoutUtils.d("HookUtils", "msg.what=" + msg.what);
            switch (msg.what) {
                case 100:
                    //for API 28以下
                    //加工 --完  一定丢给系统  secondActivity  -hook->proxyActivity---hook->    secondeActivtiy
                    handleLuachActivity(msg);
                    break;
                case 159:
                    //for API 28以上
                    handleActivity(msg);
                    break;
                default:
                    break;
            }
            //做了真正的跳转
            mH.handleMessage(msg);
            return true;
        }

        private void handleActivity(Message msg) {

            Object obj = msg.obj;
            try {
                Field mActivityCallbacksField = obj.getClass().getDeclaredField("mActivityCallbacks");
                mActivityCallbacksField.setAccessible(true);
                List<Object> mActivityCallbacks = (List<Object>) mActivityCallbacksField.get(obj);
                if (mActivityCallbacks.size() > 0) {
                    String className = "android.app.servertransaction.LaunchActivityItem";
                    if (mActivityCallbacks.get(0).getClass().getCanonicalName().equals(className)) {
                        //ProxyActivity   2
                        Object object = mActivityCallbacks.get(0);
                        Field mIntentField = object.getClass().getDeclaredField("mIntent");
                        mIntentField.setAccessible(true);
                        Intent realyIntent = (Intent) mIntentField.get(object);
                        disposeIntent(realyIntent);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
         * @Description : 还原
         * @Params :
         * @Author : daiw
         * @Date : 2018/12/9
         */
        private void handleLuachActivity(Message msg) {

            Object obj = msg.obj;
            try {
                Field intentField = obj.getClass().getDeclaredField("intent");
                intentField.setAccessible(true);
                //ProxyActivity   2
                Intent realyIntent = (Intent) intentField.get(obj);
                disposeIntent(realyIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void disposeIntent(Intent realyIntent) {
        //sconedActivity  1
        Intent oldIntent = realyIntent.getParcelableExtra("oldIntent");
        if (oldIntent != null) {
            //集中式登录
            boolean isLogin = ShareData.getInstance().getBooleanValue(ConfigConstans.USER_FLAG_ISLOGIN, false);
            if (isLogin) { //|| oldIntent.getComponent().getClassName().equals(XXX.class)
                //登录  还原  把原有的意图    放到realyIntent
                realyIntent.setComponent(oldIntent.getComponent());
            } else {
                ComponentName componentName = new ComponentName(context, LoginActivity.class);
                realyIntent.putExtra("extraIntent", oldIntent.getComponent().getClassName());
                realyIntent.setComponent(componentName);
            }
        }
    }

    /*
     * @Description : 还原 IActivityManagerSingleton 成员变量  反射  调用一次
     * @Params :
     * @Author : daiw
     * @Date : 2018/12/9
     */
    public void hookSatrtActivity(Context context) {
        this.context = context;
        try {
            Object defauleObj = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Class activityManagerClass = Class.forName("android.app.ActivityManager");
                Field IActivityManagerSingletonField = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
                IActivityManagerSingletonField.setAccessible(true);
                //因为是静态变量  所以获取的到的是系统值  hook   伪hook
                // defauleObj ==> IActivityManager
                defauleObj = IActivityManagerSingletonField.get(null);
            }else {
                Class activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
                Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
                gDefaultField.setAccessible(true);
                defauleObj = gDefaultField.get(null);
            }
            //mInstance对象
            Class singletonClass = Class.forName("android.util.Singleton");

            Field mInstance = singletonClass.getDeclaredField("mInstance");
            //还原 IactivityManager对象  系统对象
            mInstance.setAccessible(true);
            Object iActivityManagerObject = mInstance.get(defauleObj);
            Class iActivityManagerClass = Class.forName("android.app.IActivityManager");
            //第二参数  是即将返回的对象 需要实现那些接口
            Object oldIactivityManager = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{iActivityManagerClass},
                    new StartActivityInvocationHandler(iActivityManagerObject));
            //将系统的iActivityManager  替换成    自己通过动态代理实现的对象   oldIactivityManager对象  实现了 IActivityManager这个接口的所有方法
            mInstance.set(defauleObj, oldIactivityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class StartActivityInvocationHandler implements InvocationHandler {

        private Object iActivityManagerObject;

        public StartActivityInvocationHandler(Object iActivityManagerObject) {
            this.iActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogoutUtils.d("Info", "invoke-->");
            if ("startActivity".equals(method.getName())) {
                LogoutUtils.d("Info", "invoke-->startActivity");
                //瞒天过海
                //寻找传进来的intent
                Intent oldIntent = null;
                int index = 0;
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Intent) {
                        oldIntent = (Intent) args[i];
                        index = i;
                    }
                }
                //目的  ---载入acgtivity  将它还原
                Intent newIntent = new Intent();
                ComponentName componentName = new ComponentName(context, ProxyActivity.class);
                newIntent.setComponent(componentName);
                //                真实的意图 被我隐藏到了  键值对
                newIntent.putExtra("oldIntent", oldIntent);
                args[index] = newIntent;

            }
            return method.invoke(iActivityManagerObject, args);
        }
    }

}
