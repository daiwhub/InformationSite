package daiw.com.informationsite.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import daiw.com.core.rx.RxRestClient;
import daiw.com.informationsite.bean.LoginResponse;
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
        return RxRestClient.create()
                .url("user/login")
                .params(params)
                .build()
                .post()
                .flatMap(new Function<String, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(String s) throws Exception {
                        final LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                        if (loginResponse == null) {
                            return null;
                        }
                        return Observable.create(new ObservableOnSubscribe<LoginResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<LoginResponse> emitter) throws Exception {
                                emitter.onNext(loginResponse);
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {

    }
}
