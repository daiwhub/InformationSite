package daiw.com.informationsite.api.constans;

import android.os.Environment;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/7  6:13 PM
 *
 *         ***************************
 */
public class Constans {
    /**
     * true测试环境false线上环境
     */
    public static final boolean DEBUG = true;

    /**
     * 启动页广告下载地址
     */
    public static final String URL_AD_SPLASH = "https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg";
    public static final String URL_AD_SPLASH_1 = "https://ws1.sinaimg.cn/large/0065oQSqly1fw0vdlg6xcj30j60mzdk7.jpg";
    public static final String URL_AD_SPLASH_2 = "https://ws1.sinaimg.cn/large/0065oQSqgy1fze94uew3jj30qo10cdka.jpg";
    public static final String URL_AD_SPLASH_3 = "https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg";
    public static final String URL_AD_SPLASH_4 = "https://ws1.sinaimg.cn/large/0065oQSqly1fymj13tnjmj30r60zf79k.jpg";
    public static final String URL_AD_SPLASH_5 = "https://ws1.sinaimg.cn/large/0065oQSqgy1fy58bi1wlgj30sg10hguu.jpgg";
    /**
     * 启动页广告存储路径
     */
    public static final String PATH_AD_SPLASH =Environment.getExternalStorageDirectory() + "/daiw/Image/splashad/splash.jpg";
    /**
     * 缓存启动页广告标识
     */
    public static final String SHARE_AD_SPLASH_FLAG = "share_ad_splash_flag";

}
