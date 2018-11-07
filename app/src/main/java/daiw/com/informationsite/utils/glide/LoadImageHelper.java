package daiw.com.informationsite.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/****************************
 * 类描述：图片加载隔离类
 *
 * @author: daiw
 * @time: 2018/11/5  6:11 PM
 *
 *         ***************************
 */
public class LoadImageHelper implements ILoadImage{

    private static LoadImageHelper loadImageHelper;
    public static LoadImageHelper getInstanse(){
        if(loadImageHelper == null){
            synchronized (LoadImageHelper.class){
                if(loadImageHelper == null){
                    loadImageHelper = new LoadImageHelper();
                }
            }
        }
        return loadImageHelper;
    }


    @Override
    public void loadImage(Context context, int res, ImageView imageView) {
        GlideProcessor.getInstance().loadImage(context,res,imageView);
    }

    @Override
    public void loadImage(Context context, File file, ImageView imageView) {
        GlideProcessor.getInstance().loadImage(context,file,imageView);
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        GlideProcessor.getInstance().loadImage(context,url,imageView);
    }

    @Override
    public void loadImage(Context context, Bitmap bitmap, ImageView imageView) {
        GlideProcessor.getInstance().loadImage(context,bitmap,imageView);
    }
}
