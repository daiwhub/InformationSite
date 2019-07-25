package daiw.com.informationsite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View

import com.liulishuo.filedownloader.FileDownloader

import java.util.Random

import daiw.com.informationsite.api.constans.Constans
import daiw.com.informationsite.base.BaseActivity
import daiw.com.informationsite.manager.AppManager
import daiw.com.informationsite.manager.StartActivityManager
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd
import daiw.com.informationsite.view.splash.SplashActivity

/****************************
 * 类描述：首页
 *
 * @author: daiw
 * @time: 2018/11/19  11:51 PM
 *
 * ***************************
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    private val urls = arrayOf(Constans.URL_AD_SPLASH_1, Constans.URL_AD_SPLASH_2, Constans.URL_AD_SPLASH_3, Constans.URL_AD_SPLASH_4, Constans.URL_AD_SPLASH_5)

    private var firstTime: Long = 0

    init {
        //隐藏顶部导航栏
        isShowToolbar(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //登录
        findViewById<View>(R.id.login).setOnClickListener { StartActivityManager.startLoginActivity(App.getContext()) }
        //设置
        findViewById<View>(R.id.setting).setOnClickListener { StartActivityManager.startSettingActivity(App.getContext()) }

        initFileDownloader()
        val index = Random().nextInt(4) + 1
        val url = urls[index]
        DownLoadSplashAd.downLoadSplashAd(applicationContext, url)
    }

    override fun initView() {

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }

    /*
     * @Description :初始化下载文件
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/7
     */
    private fun initFileDownloader() {
        FileDownloader.setup(this)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            val secondTime = System.currentTimeMillis()
            if (secondTime - firstTime > 2000) {
                showShortToast(R.string.exit_again)
                firstTime = secondTime
                return true
            } else {
                //                AppManager.getInstance().removeActivityFromName(SplashActivity.class.getSimpleName());
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
