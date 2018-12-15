package daiw.com.informationsite.mvp.percenter;

import android.text.TextUtils;

import daiw.com.informationsite.App;
import daiw.com.informationsite.R;
import daiw.com.informationsite.base.BasePercenter;
import daiw.com.informationsite.bean.LoginResponse;
import daiw.com.informationsite.http.result.HttpResultFailureSubscriber;
import daiw.com.informationsite.http.result.HttpResultSuccessSubscriber;
import daiw.com.informationsite.mvp.contract.login.ILoginContract;
import daiw.com.informationsite.mvp.model.LoginModel;

/****************************
 * 类描述：登录请求
 *
 * @author: daiw
 * @time: 2018/11/20  11:52 PM
 *
 *         ***************************
 */
public class LoginPercenter extends BasePercenter<ILoginContract.ILoginView,
        ILoginContract.ILoginModel> {

    public LoginPercenter(ILoginContract.ILoginView view){
        super(view,new LoginModel());
    }
     /*
      * @Description : 请求登录
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    public void login(String username, String passworld){
        //开始加载动画
        if(mView != null){
            mView.showPragress();
        }
        addDisposable(mModel.login(username,passworld).subscribe(new HttpResultSuccessSubscriber<LoginResponse>() {

            @Override
            public void _onSuccess(LoginResponse loginResponse) {
                if(mView != null){
                    mView.dissPragress();
                    if(loginResponse != null){
                        if(loginResponse.getErrorCode() == 0){
                            mView.showToast(App.getContext().getResources().getString(R.string.login_success));
                            mView.loginSuccess();
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
                    mView.dissPragress();
                    mView.loginError(t.getMessage());
                }
            }
        }));
    }
     /*
      * @Description : 数据校验
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/9
      */
    public void checkData(String userName,String passWord){
        if(mView == null){
            return;
        }
        if(TextUtils.isEmpty(userName)){
           mView.showToast(R.string.the_account_cannot_be_empty);
           return;
        }
        if(TextUtils.isEmpty(passWord)){
            mView.showToast(R.string.the_password_cannot_be_empty);
            return;
        }
        //请求登录
        login(userName,passWord);
    }

}
