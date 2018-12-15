package daiw.com.informationsite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.liulishuo.filedownloader.FileDownloader;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.base.BaseActivity;
import daiw.com.informationsite.manager.AppManager;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd;
import daiw.com.informationsite.view.splash.SplashActivity;
/****************************
 * 类描述：首页
 *
 * @author: daiw
 * @time: 2018/11/19  11:51 PM
 *
 *         ***************************
 */
public class MainActivity extends BaseActivity {

    public MainActivity() {
        super(R.layout.activity_main);
        //隐藏顶部导航栏
        isShowToolbar(false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityManager.startLoginActivity(App.getContext());
            }
        });

        initFileDownloader();
        DownLoadSplashAd.downLoadSplashAd(getApplicationContext(), Constans.URL_AD_SPLASH);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*
     * @Description :初始化下载文件
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/7
     */
    private void initFileDownloader() {
        FileDownloader.setup(this);
    }

    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            long secondTime = System.currentTimeMillis();
            if(secondTime - firstTime > 2000){
                showShortToast(R.string.exit_again);
                firstTime = secondTime;
                return true;
            }else {
//                AppManager.getInstance().removeActivityFromName(SplashActivity.class.getSimpleName());
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
