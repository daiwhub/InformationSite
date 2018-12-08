package daiw.com.informationsite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liulishuo.filedownloader.FileDownloader;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityManager.startLoginActivity(App.getContext());
            }
        });

        initFileDownloader();
        DownLoadSplashAd.downLoadSplashAd(getApplicationContext(),Constans.URL_AD_SPLASH);
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
