package daiw.com.core.rx;

import org.json.JSONObject;

import java.util.Map;

import daiw.com.core.net.Constancts;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  5:12 PM
 *
 *         ***************************
 */
public interface RxRestService {

    @GET
    Observable<JSONObject> get(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<JSONObject> post(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT
    Observable<JSONObject> put(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Observable<JSONObject> delete(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载是直接到内存,所以需要 @Streaming
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Observable<JSONObject> upload(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @Part MultipartBody.Part file);

    /**
     * 原始数据
     */
    @POST
    Observable<JSONObject> postRaw(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @Body RequestBody body);

    @PUT
    Observable<JSONObject> putRaw(@Header(Constancts.URL_HEADER_KEY) String headerKey, @Url String url, @Body RequestBody body);
}
