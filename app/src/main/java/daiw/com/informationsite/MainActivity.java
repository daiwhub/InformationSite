package daiw.com.informationsite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liulishuo.filedownloader.FileDownloader;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.login.model.ModelLogin;
import daiw.com.informationsite.login.percenter.PercenterLogin;
import daiw.com.informationsite.login.view.ILoginView;
import daiw.com.informationsite.utils.download.files.DownLoadSplashAd;
import daiw.com.informationsite.utils.log.LogoutUtils;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PercenterLogin percenterLogin = new PercenterLogin(this);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelLogin m = new ModelLogin("daiw","123qwe");

                percenterLogin.fetch(m);

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

    @Override
    public void loginSuccess() {
        LogoutUtils.d("登录成功111");
    }

    @Override
    public void loginFailure(int errorCode) {

    }
}
