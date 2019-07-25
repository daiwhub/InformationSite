package daiw.com.core.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import daiw.com.core.app.Configurator;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/****************************
 * 类描述：多BaseUrl拦截器
 *
 * @author: daiw
 * @time: 2018/11/22  11:26 PM
 *
 *         ***************************
 */
public class BaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取request
        Request request = chain.request();

        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers(Constancts.URL_HEADER_KEY);
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader(Constancts.URL_HEADER_KEY);
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            HashMap<String,String> headerkeys = Configurator.getInstance().getHeaderkeys();
            if(headerkeys.isEmpty()){
                chain.proceed(request);
            }
            //初始化
            newBaseUrl = oldHttpUrl;

            for(Map.Entry<String,String> entry : headerkeys.entrySet()){
                if(!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getKey())){
                    if(entry.getKey().equals(headerValue)){
                        newBaseUrl = HttpUrl.parse(entry.getValue());
                        break;
                    }
                }
            }
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            Log.e("Url", "intercept: "+newFullUrl.toString());
            return chain.proceed(builder.url(newFullUrl).build());
        }
        return chain.proceed(request);
    }
}
