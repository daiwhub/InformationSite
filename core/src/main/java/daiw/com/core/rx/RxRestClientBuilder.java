package daiw.com.core.rx;

import java.io.File;
import java.util.HashMap;

import daiw.com.core.net.RestClient;
import daiw.com.core.net.callback.IError;
import daiw.com.core.net.callback.IFailure;
import daiw.com.core.net.callback.IRequest;
import daiw.com.core.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  6:10 PM
 *
 *         ***************************
 */
public class RxRestClientBuilder {

    private HashMap<String, Object> mParams;
    private String mUrl;
    private RequestBody mBody;
    private String mHeaderKey;

    private File mFile;

    public RxRestClientBuilder() {
    }

    public final RxRestClientBuilder headerKey(String headerKey){
        this.mHeaderKey = headerKey;
        return this;
    }

    public final RxRestClientBuilder params(HashMap<String, Object> params){
        this.mParams = params;
        return this;
    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }
    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mHeaderKey,mParams,mUrl,mBody,mFile);
    }
}
