package daiw.com.informationsite.view.splash;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import daiw.com.informationsite.R;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.view.custom.progessbar.RoundProgressBar;

public class SplashActivity extends AppCompatActivity {

    private int mTotalProgress;
    private int mCurrentProgress;

    private ImageView adImage;

    private RoundProgressBar rpBar;

    private CountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBaseTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initVariable();
        initView();
        initEnevt();

        initStartCountDown();

    }

    private void initEnevt() {
        rpBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转首页
                StartActivityManager.startManiActivity(SplashActivity.this);
                finish();
            }
        });
    }

    private void initStartCountDown() {

        downTimer = new CountDownTimer(5500,50) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(mCurrentProgress < mTotalProgress){
                    mCurrentProgress += 1;

                    rpBar.setProgress(mCurrentProgress);
                }
            }

            @Override
            public void onFinish() {
                //跳转首页
                StartActivityManager.startManiActivity(SplashActivity.this);
                finish();
            }
        }.start();

    }


    private void initVariable() {
        mTotalProgress = 100;
        mCurrentProgress = 0;
    }

    private void initView() {
        //广告图
        adImage = findViewById(R.id.ac_splash_ad_iv);
        //跳过
        rpBar = findViewById(R.id.ac_splash_skip_pg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(downTimer != null){
            downTimer.cancel();
        }
    }
}
