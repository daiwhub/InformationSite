package daiw.com.core.rx;

import java.io.File;
import java.util.HashMap;

import daiw.com.core.net.HttpMethod;
import daiw.com.core.net.RestClientBuilder;
import daiw.com.core.net.RestCreator;
import daiw.com.core.net.RestService;
import daiw.com.core.net.callback.IError;
import daiw.com.core.net.callback.IFailure;
import daiw.com.core.net.callback.IRequest;
import daiw.com.core.net.callback.ISuccess;
import daiw.com.core.net.callback.RequestCallbacks;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  5:38 PM
 *
 *         ***************************
 */
public class RxRestClient {

    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final  RequestBody BODY;

    private final File FILE;

    public RxRestClient(HashMap<String,
            Object> PARAMS,
                        String URL,
                        RequestBody BODY,
                        File FILE) {
        this.PARAMS = PARAMS;
        this.URL = URL;
        this.BODY = BODY;
        this.FILE = FILE;
    }

    public static RxRestClientBuilder create(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod httpMethod) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        switch (httpMethod) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            case DOWNLOAD:
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
       return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        return request(HttpMethod.POST);
    }

    public final Observable<String> put() {
        return request(HttpMethod.PUT);
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }
}
