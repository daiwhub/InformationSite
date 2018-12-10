package daiw.com.informationsite.manager;

import android.app.Activity;
import android.text.TextUtils;

import java.util.Stack;

/****************************
 * 类描述：应用程序Activity管理类
 *
 * @author: daiw
 * @time: 2018/12/10  12:03 AM
 *
 *         ***************************
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    public static AppManager getInstance(){
        if(instance == null){
            synchronized (AppManager.class){
                if(instance == null){
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }
     /*
      * @Description : 添加Activity到堆栈
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
    public void addActivity(Activity activity){
        if(activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }
     /*
      * @Description : 获取当前Activity（堆栈中最后一个压入的）
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }
     /*
      * @Description : 结束当前Activity（堆栈中最后一个压入的）
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
    public void removeActivity(){
       Activity activity = activityStack.lastElement();
       removeActivity(activity);
    }
     /*
      * @Description : 结束指定的Activity
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
    public void removeActivity(Activity activity) {
        if(activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
     /*
      * @Description : 判断指定类名的activity是否在任务中
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
     public boolean containActivity(Class<?> cls){
         for(Activity activity : activityStack){
             if(activity.getClass().equals(cls)){
                 if(activity != null){
                     return true;
                 }
             }
         }
         return false;
     }
      /*
       * @Description : 结束所有Activity
       * @Params :
       * @Author : daiw
       * @Date : 2018/12/10
       */
     public void finishAllActivity(){
         if(activityStack == null){
             return;
         }
         for(int i = 0;i<activityStack.size();i++){
             Activity activity = activityStack.get(i);
             if(activity != null){
                 activity.finish();
                 activity = null;
             }
         }
         activityStack.clear();
     }
     /*
      * @Description : 移除指定名字的activity
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/10
      */
     public void removeActivityFromName(String activityName){
         if(activityStack == null || TextUtils.isEmpty(activityName)){
             return;
         }
         for(int i = 0;i<activityStack.size();i++){
             if(activityName.equals(activityStack.get(i).getClass().getSimpleName())){
                 activityStack.get(i).finish();
             }
         }
     }
      /*
       * @Description : 移除除了指定activity的所有activity
       * @Params :
       * @Author : daiw
       * @Date : 2018/12/10
       */
      public void finishAllExcept(String activityName){
          if(activityStack == null || TextUtils.isEmpty(activityName)){
              return;
          }
          for(int i = 0;i<activityStack.size();i++){
              if(! activityName.equals(activityStack.get(i).getClass().getSimpleName())){
                  activityStack.get(i).finish();
              }
          }
      }
}
