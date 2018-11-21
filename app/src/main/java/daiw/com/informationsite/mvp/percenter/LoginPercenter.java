package daiw.com.informationsite.mvp.percenter;

import daiw.com.informationsite.base.BasePercenter;
import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.http.result.HttpResultFailureSubscriber;
import daiw.com.informationsite.interf.login.ILoginContract;
import daiw.com.informationsite.http.result.HttpResultSuccessSubscriber;
import daiw.com.informationsite.mvp.model.LoginModel;

/****************************
 * 类描述：登录请求
 *
 * @author: daiw
 * @time: 2018/11/20  11:52 PM
 *
 *         ***************************
 */
public class LoginPercenter extends BasePercenter {

    public LoginPercenter(ILoginContract.ILoginView view){
        super(view,new LoginModel());
    }

    public void login(String username, String passworld){
        addDisposable(mModel.login(username,passworld).subscribe(new HttpResultSuccessSubscriber<LoginResponse>() {

            @Override
            public void _onSuccess(LoginResponse loginResponse) {

            }
        }, new HttpResultFailureSubscriber() {
            @Override
            public void _onFailure(Throwable t) {

            }
        }));
    }

}
