package daiw.com.informationsite.mvp.model;

import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.interf.login.ILoginContract;
import daiw.com.informationsite.interf.mvp.IModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/20  11:53 PM
 *
 *         ***************************
 */
public class LoginModel implements ILoginContract.ILoginModel {

    public Observable<LoginResponse> login(String username, String passworld){
        return new Observable<LoginResponse>() {
            @Override
            protected void subscribeActual(Observer<? super LoginResponse> observer) {

            }
        };
    }

    @Override
    public void onDestroy() {

    }
}
