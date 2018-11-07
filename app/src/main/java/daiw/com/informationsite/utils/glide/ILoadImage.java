package daiw.com.informationsite.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/5  6:02 PM
 *
 *         ***************************
 */
public interface ILoadImage {

    void loadImage(Context context, int res, ImageView imageView);
    void loadImage(Context context, File file, ImageView imageView);
    void loadImage(Context context, String url, ImageView imageView);
    void loadImage(Context context, Bitmap bitmap, ImageView imageView);

}
