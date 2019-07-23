package daiw.com.informationsite.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;

import java.io.File;

import daiw.com.informationsite.R;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/5  6:06 PM
 *
 *         ***************************
 */
public class GlideProcessor implements ILoadImage{

    private static GlideProcessor glideProcessor;

    public static GlideProcessor getInstance(){
        if(glideProcessor == null){
            synchronized (GlideProcessor.class){
                if(glideProcessor == null){
                    glideProcessor = new GlideProcessor();
                }
            }
        }
        return glideProcessor;
    }

    @Override
    public void loadImage(Context context, int res, ImageView imageView) {
        GlideApp.with(context)
                .load(res)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, File file, ImageView imageView) {
        GlideApp.with(context)
                .load(file)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, Bitmap bitmap, ImageView imageView) {
        GlideApp.with(context)
                .load(bitmap)
                .fitCenter()
                .into(imageView);
    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
// BusUtil.getBus().post(new GlideCacheClearSuccessEvent());
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
    }

}
