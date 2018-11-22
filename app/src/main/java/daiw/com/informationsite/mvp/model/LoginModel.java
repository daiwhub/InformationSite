package daiw.com.informationsite.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import daiw.com.core.rx.RxRestClient;
import daiw.com.informationsite.R;
import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.http.ApiConstants;
import daiw.com.informationsite.http.result.HttpResultChange;
import daiw.com.informationsite.interf.login.ILoginContract;
import daiw.com.informationsite.interf.mvp.IModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/20  11:53 PM
 *
 *         ***************************
 */
public class LoginModel implements ILoginContract.ILoginModel {

    public Observable<LoginResponse> login(String username, String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        return HttpResultChange.getHttpResultChange(RxRestClient.create()
                        .headerKey(ApiConstants.URL_HEADER_KEY_1)
                        .url(ApiConstants.URL_LOGIN)
                        .params(params)
                        .build()
                        .post(), LoginResponse.class)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {

    }
}
