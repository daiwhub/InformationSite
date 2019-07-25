package daiw.com.informationsite.manager

import android.content.Context
import android.content.Intent

import daiw.com.informationsite.MainActivity
import daiw.com.informationsite.view.login.LoginActivity
import daiw.com.informationsite.view.register.RegisterActivity
import daiw.com.informationsite.view.setting.SettringActivity

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/5  4:43 PM
 *
 * ***************************
 */
object StartActivityManager {
    /*
      * @Description :跳转首页
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/5
      */
    fun startManiActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)˚
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /*
      * @Description : 跳转登录页面
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/22
      */
    fun startLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /*
      * @Description : 跳转注册
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    fun startRegisterActivity(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /*
      * @Description : 跳转设置
      * @Params :
      * @Author : daiw
      * @Date : 2019/07/26
      */
    fun startSettingActivity(context: Context){
        val intent = Intent(context,SettringActivity::class.java)
        context.startActivity(intent)
    }
}
