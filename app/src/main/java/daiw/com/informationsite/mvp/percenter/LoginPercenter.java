package daiw.com.informationsite.mvp.percenter;

import daiw.com.informationsite.App;
import daiw.com.informationsite.R;
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
                if(mView != null){
                    if(loginResponse != null){
                        if(loginResponse.getErrorCode() == 0){
                            mView.showToast(App.getContext().getResources().getString(R.string.login_success));
                            return;
                        }
                    }
                    mView.loginError(App.getContext().getResources().getString(R.string.login_failure));
                }
            }
        }, new HttpResultFailureSubscriber() {
            @Override
            public void _onFailure(Throwable t) {
                if(mView != null){
                    mView.loginError(t.getMessage());
                }
            }
        }));
    }

}
