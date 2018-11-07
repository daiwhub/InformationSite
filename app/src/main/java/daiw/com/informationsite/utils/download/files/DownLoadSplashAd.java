package daiw.com.informationsite.utils.download.files;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import daiw.com.informationsite.api.constans.Constans;
import daiw.com.informationsite.utils.download.FileDownloaderUtil;
import daiw.com.informationsite.utils.download.IDownLoadCallback;
import daiw.com.informationsite.utils.file.FileUtil;
import daiw.com.informationsite.utils.log.LogoutUtils;
import daiw.com.informationsite.utils.sharedata.ShareData;

/****************************
 * 类描述：下载启动页广告
 *
 * @author: daiw
 * @time: 2018/11/7  5:29 PM
 *
 *         ***************************
 */
public class DownLoadSplashAd {
    private static final String TAG = DownLoadSplashAd.class.getCanonicalName();

    public static void downLoadSplashAd (Context context,String url){
        //判断SD卡中daiw文件夹是否存在，存在则判断Image文件夹是否存在，接着判断splashad文件夹是否存在，不存在则直接下载

        //判断文件夹是否存在
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if(! sdCardExist){
            LogoutUtils.e(TAG,"SD卡不存在！！！");
            return;
        }

        File sd=Environment.getExternalStorageDirectory();
        String path=sd.getPath()+"/daiw/Image/splashad";
        //判断文件夹是否存在，不存在直接创建
        FileUtil.fileIsExists(path);
        String filePath = path + "/splash.jpg";
        //判断文件是否存在
        if(FileUtil.fileIsExistsCall(filePath)){
            //存在则删除
            FileUtil.deleteFile(new File(filePath));
        }
        //下载文件
        FileDownloaderUtil.getInstance().initDownFile(context, filePath, url, new IDownLoadCallback() {
            @Override
            public void pending() {
                LogoutUtils.d(TAG,"开始下载启动广告！！！");
            }

            @Override
            public void progress(int soFarBytes, int totalBytes) {
                int progress = soFarBytes * 100 / totalBytes;
                LogoutUtils.d(TAG,"广告下载进度为：" + progress);
            }

            @Override
            public void completed() {
                //缓存已下载广告页图片标识
                ShareData.getInstance().saveBooleanValue(Constans.SHARE_AD_SPLASH_FLAG,true);
                Log.d(TAG,"启动广告下载完成！！！");
            }

            @Override
            public void paused(int soFarBytes, int totalBytes) {
                LogoutUtils.d(TAG,"下载启动广告暂停！！！");
            }

            @Override
            public void error(Throwable e) {
                LogoutUtils.d(TAG,"下载启动广告出错！！！");
            }

            @Override
            public void warn() {
                LogoutUtils.d(TAG,"已存在同样的下载！！！");
            }
        });

        if (sdCardExist) {
            //文件夹存在
        }else {
            LogoutUtils.e(TAG,"文件夹不存在");
        }
    }

}
