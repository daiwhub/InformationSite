package daiw.com.informationsite.interf.login;

import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.interf.mvp.IModel;
import daiw.com.informationsite.interf.mvp.IView;
import daiw.com.informationsite.login.model.ILoginModel;
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

        void loginError(String errorMessage);
        void dissmissDialog();

    }

    interface ILoginModel extends IModel {
        Observable<LoginResponse> login(String username, String passworld);
    }
}
