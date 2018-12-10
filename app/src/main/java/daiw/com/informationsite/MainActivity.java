package daiw.com.informationsite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liulishuo.filedownloader.FileDownloader;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.base.BaseActivity;
import daiw.com.informationsite.manager.AppManager;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd;
import daiw.com.informationsite.view.splash.SplashActivity;

public class MainActivity extends BaseActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityManager.startLoginActivity(App.getContext());
            }
        });

        initFileDownloader();
        DownLoadSplashAd.downLoadSplashAd(getApplicationContext(),Constans.URL_AD_SPLASH);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppManager.getInstance().removeActivityFromName(SplashActivity.class.getSimpleName());
    }

    /*
      * @Description :初始化下载文件
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    private void initFileDownloader(){
        FileDownloader.setup(this);
    }

}
