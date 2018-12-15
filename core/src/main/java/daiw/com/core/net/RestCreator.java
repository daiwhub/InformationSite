package daiw.com.core.net;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import daiw.com.core.app.ConfigKeys;
import daiw.com.core.app.ProjectInit;
import daiw.com.core.rx.RxRestService;
import daiw.com.core.util.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  5:23 PM
 *
 *         ***************************
 */
public final class RestCreator {

   /**
    *产生一个全局的Retrofit客户端
    */

   private static final class RetrofitHolder{
       private static final String BASE_URL = ProjectInit.getConfiguration(ConfigKeys.API_HOST.name());
       private static final Retrofit REST_SERVICE = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .client(OKHttpClientHolder.OK_HTTP_CLIENT)
               .build();
   }

   private static final class  OKHttpClientHolder{
       private static final int TIME_OUT = 60;

       static HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger())
               .setLevel(HttpLoggingInterceptor.Level.BODY);

       private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
               .addInterceptor(new BaseUrlInterceptor())
               .addNetworkInterceptor(logInterceptor)
               .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
               .build();
   }

    private static class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.d(mMessage.toString());
            }
        }
    }


   /**
    *提供接口让调用者得到retrofit对象
    */
   private static final class RestServiceHolder{
       private static final RestService REST_SERVICE = RetrofitHolder.REST_SERVICE.create(RestService.class);
   }

   /**
    *获取对象
    */
   public static RestService getRestService(){
       return RestServiceHolder.REST_SERVICE;
   }
    /**
     *提供接口让调用者得到retrofit对象
     */
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE = RetrofitHolder.REST_SERVICE.create(RxRestService.class);
    }

    /**
     *获取对象
     */
    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }

}
