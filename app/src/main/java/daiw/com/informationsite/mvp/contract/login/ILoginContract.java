package daiw.com.informationsite.mvp.contract.login;

import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.interf.mvp.IModel;
import daiw.com.informationsite.interf.mvp.IView;
import io.reactivex.Observable;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/20  11:47 PM
 *
 *         ***************************
 */
public interface ILoginContract {

    interface ILoginView extends IView{
        void loginSuccess();
        void loginError(String errorMessage);
    }

    interface ILoginModel extends IModel {
        Observable<LoginResponse> login(String username, String passworld);
    }
}
