package daiw.com.informationsite.mvp.model;

import java.util.HashMap;

import daiw.com.core.rx.RxRestClient;
import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.http.ApiConstants;
import daiw.com.informationsite.http.params.ParamsRequest;
import daiw.com.informationsite.http.result.HttpResultChange;
import daiw.com.informationsite.mvp.contract.login.ILoginContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/****************************
 * 类描述：登录Model
 *
 * @author: daiw
 * @time: 2018/11/20  11:53 PM
 *
 *         ***************************
 */
public class LoginModel implements ILoginContract.ILoginModel {

    public Observable<LoginResponse> login(String username, String password) {

        return HttpResultChange.getHttpResultChange(RxRestClient.create()
                        .headerKey(ApiConstants.URL_HEADER_KEY_1)
                        .url(ApiConstants.URL_LOGIN)
                        .params(ParamsRequest.getInstance().getLoginRequestParams(username,password))
                        .build()
                        .post(), LoginResponse.class)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {

    }
}
