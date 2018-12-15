package daiw.com.informationsite.mvp.model;

import daiw.com.core.rx.RxRestClient;
import daiw.com.informationsite.bean.RegisterResponse;
import daiw.com.informationsite.http.ApiConstants;
import daiw.com.informationsite.http.params.ParamsRequest;
import daiw.com.informationsite.http.result.HttpResultChange;
import daiw.com.informationsite.mvp.contract.register.IRegisterContract;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/12/15  12:40 AM
 *
 *         ***************************
 */
public class RegisterModel implements IRegisterContract.IRegisterModel {

    @Override
    public Observable<RegisterResponse> regiser(String username, String password) {
        return HttpResultChange.getHttpResultChange(RxRestClient.create()
                .headerKey(ApiConstants.URL_HEADER_KEY_1)
                .url(ApiConstants.URL_REGISTER)
                .params(ParamsRequest.getInstance().getRegisterRequestParams(username, password))
                .build()
                .post(), RegisterResponse.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {

    }
}
