package daiw.com.informationsite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liulishuo.filedownloader.FileDownloader;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
