package daiw.com.informationsite.utils.download;

import android.content.Context;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

/****************************
 * 类描述：文件下载隔离类
 *
 * @author: daiw
 * @time: 2018/11/6  12:02 PM
 *
 *         ***************************
 */
public class FileDownloaderUtil {

    private static FileDownloaderUtil fileDownloaderUtil;

    public static FileDownloaderUtil getInstance() {
        if (fileDownloaderUtil == null) {
            synchronized (FileDownloaderUtil.class) {
                if (fileDownloaderUtil == null) {
                    fileDownloaderUtil = new FileDownloaderUtil();
                }
            }
        }
        return fileDownloaderUtil;
    }
     /*
      * @Description :下载开始
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/7
      */
    public void initDownFile(Context context, String path, String url, final IDownLoadCallback callback) {
        FileDownloader.getImpl().create(url)
                .setPath(path)
                .setForceReDownload(true)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        //开始前等待
                        callback.pending();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        //下载进度回调
                        callback.progress(soFarBytes, totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        //完成下载
                        callback.completed();
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        //暂停
                        callback.paused(soFarBytes, totalBytes);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        //下载出错
                        callback.error(e);
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        //已存在相同下载
                        callback.warn();
                    }
                }).start();
    }


}
