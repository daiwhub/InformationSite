package daiw.com.core.rx;

import org.json.JSONObject;

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
    private final String HEADERKEY;
    private final RequestBody BODY;

    private final File FILE;

    public RxRestClient(String HEADERKEY,
                        HashMap<String, Object> PARAMS,
                        String URL,
                        RequestBody BODY,
                        File FILE) {
        this.HEADERKEY = HEADERKEY;
        this.PARAMS = PARAMS;
        this.URL = URL;
        this.BODY = BODY;
        this.FILE = FILE;
    }

    public static RxRestClientBuilder create() {
        return new RxRestClientBuilder();
    }

    private Observable<JSONObject> request(HttpMethod httpMethod) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<JSONObject> observable = null;
        switch (httpMethod) {
            case GET:
                observable = service.get(HEADERKEY, URL, PARAMS);
                break;
            case POST:
                observable = service.post(HEADERKEY, URL, PARAMS);
                break;
            case PUT:
                observable = service.put(HEADERKEY, URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(HEADERKEY, URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                observable = service.upload(HEADERKEY, URL, body);
                break;
            case DOWNLOAD:
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<JSONObject> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<JSONObject> post() {
        return request(HttpMethod.POST);
    }

    public final Observable<JSONObject> put() {
        return request(HttpMethod.PUT);
    }

    public final Observable<JSONObject> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<JSONObject> upload() {
        return request(HttpMethod.UPLOAD);
    }
}
