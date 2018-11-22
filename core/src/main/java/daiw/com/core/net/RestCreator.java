package daiw.com.core.net;

import java.util.concurrent.TimeUnit;

import daiw.com.core.app.ConfigKeys;
import daiw.com.core.app.ProjectInit;
import daiw.com.core.rx.RxRestService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
               .addConverterFactory(ScalarsConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .client(OKHttpClientHolder.OK_HTTP_CLIENT)
               .build();
   }

   private static final class  OKHttpClientHolder{
       private static final int TIME_OUT = 60;
       private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
               .addInterceptor(new BaseUrlInterceptor())
               .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
               .build();
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
