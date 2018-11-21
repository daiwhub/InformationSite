package daiw.com.core.net;

import java.io.File;
import java.security.PublicKey;
import java.util.HashMap;

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
public class RestClientBuilder {

    private HashMap<String, Object> mParams;
    private String mUrl;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private RequestBody mBody;

    private File mFile;

    private String mDownloadDir;
    private String mExtension;
    private String mFileName;

    public RestClientBuilder() {
    }

    public final RestClientBuilder params(HashMap<String, Object> params){
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder request(IRequest request){
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success){
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder error(IError error){
        this.mError = error;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure){
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }
    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder dir(String dir){
        this.mDownloadDir = dir;
        return this;
    }
    public final RestClientBuilder extension(String extension){
        this.mExtension = extension;
        return this;
    }
    public final RestClientBuilder filename(String filename){
        this.mFileName = filename;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mParams,mUrl,mRequest,mSuccess,mError,mFailure,mBody,mFile,mDownloadDir,mExtension,mFileName);
    }
}
