package daiw.com.informationsite.view.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import daiw.com.informationsite.R;
import daiw.com.informationsite.view.custom.progessbar.RoundProgressBar;

public class SplashActivity extends AppCompatActivity {

    private int mTotalProgress;
    private int mCurrentProgress;

    private RoundProgressBar rpBar01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBaseTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initVariable();
        initView();

        new Thread(new ProgressRunable()).start();
    }

    private void initVariable() {
        mTotalProgress = 100;
        mCurrentProgress = 0;
    }

    private void initView() {
        rpBar01 = findViewById(R.id.ac_splash_skip_pg);
    }

    class ProgressRunable implements Runnable{

        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress){
                mCurrentProgress += 1;

                rpBar01.setProgress(mCurrentProgress);

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
