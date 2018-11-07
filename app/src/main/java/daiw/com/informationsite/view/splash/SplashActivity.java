package daiw.com.informationsite.view.splash;

import android.Manifest;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import daiw.com.informationsite.R;
import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.interf.PermissionsRequestInterf;
import daiw.com.informationsite.manager.PermissionManager;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.utils.ToastUtil;
import daiw.com.informationsite.utils.file.FileUtil;
import daiw.com.informationsite.utils.glide.LoadImageHelper;
import daiw.com.informationsite.utils.log.LogoutUtils;
import daiw.com.informationsite.utils.sharedata.ShareData;
import daiw.com.informationsite.view.custom.progessbar.RoundProgressBar;

public class SplashActivity extends AppCompatActivity implements PermissionsRequestInterf {
    private static final String TAG = SplashActivity.class.getCanonicalName();

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

        initPermissions();

    }

    private void initEnevt() {
        //跳过
        rpBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downTimer != null){
                    downTimer.cancel();
                }
                //跳转首页
                StartActivityManager.startManiActivity(SplashActivity.this);
                finish();
            }
        });
    }
     /*
      * @Description :启动倒计时
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
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
     /*
      * @Description :加载广告图
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    private void showSplashAd(){
        boolean cacheAdSplashFlag = ShareData.getInstance().
                getBooleanValue(Constans.SHARE_AD_SPLASH_FLAG,false);
//        File file = Environment.getExternalStorageDirectory();
        String filePath = Constans.PATH_AD_SPLASH;
        if(! cacheAdSplashFlag){
            //缓存不存在(首次安装或者清空缓存后首次进入)
            //判断文件是否存在
            if(FileUtil.fileIsExistsCall(filePath)){
                //存在则清空
                File file = new File(filePath);
                if(file != null) {
                    FileUtil.deleteFile(file);

                }
            }
        }else {
            //判断文件夹是否存在
            boolean sdCardExist = Environment.getExternalStorageState()
                    .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
            if (!sdCardExist) {
                LogoutUtils.e(TAG, "SD卡不存在！！！");
                return;
            }
            //判断文件是否存在
            if (FileUtil.fileIsExistsCall(filePath)) {
                //存在则
                File file = new File(filePath);
                if (file != null && adImage != null) {
                    LoadImageHelper.getInstanse().loadImage(getApplicationContext(), file, adImage);
                    return;
                }
            }
        }
        //如果本地图片不存则加载网络图片
        if(adImage != null) {
            LoadImageHelper.getInstanse().loadImage(getApplicationContext(), Constans.URL_AD_SPLASH_1, adImage);
        }
    }

     /*
      * @Description :动态申请权限
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    private void initPermissions() {
        PermissionManager.getInstance().triggerPermissionRequest(this,
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

        @Override
    protected void onDestroy() {
        super.onDestroy();
        if(downTimer != null){
            downTimer.cancel();
        }
    }

    @Override
    public void onSuccess() {
        initEnevt();
        showSplashAd();
        initStartCountDown();
    }

    @Override
    public void onError(int errorCode) {
        ToastUtil.show(getApplicationContext(),"相关权限获取失败，请到设置中给与相关权限");
    }
}
