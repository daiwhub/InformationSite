package daiw.com.informationsite.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

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
}
