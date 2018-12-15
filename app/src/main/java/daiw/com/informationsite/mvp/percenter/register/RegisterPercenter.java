package daiw.com.informationsite.mvp.percenter.register;

import android.text.TextUtils;

import daiw.com.informationsite.App;
import daiw.com.informationsite.R;
import daiw.com.informationsite.base.BasePercenter;
import daiw.com.informationsite.bean.RegisterResponse;
import daiw.com.informationsite.http.result.HttpResultFailureSubscriber;
import daiw.com.informationsite.http.result.HttpResultSuccessSubscriber;
import daiw.com.informationsite.mvp.contract.register.IRegisterContract;
import daiw.com.informationsite.mvp.model.RegisterModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/12/15  12:47 AM
 *
 *         ***************************
 */
public class RegisterPercenter extends BasePercenter<IRegisterContract.IRegisterView,
        IRegisterContract.IRegisterModel> {

    public RegisterPercenter(IRegisterContract.IRegisterView view) {
        super(view, new RegisterModel());
    }
     /*
      * @Description : 请求注册
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    public void regiser(String username, String passworld){
        //开始加载动画
        if(mView != null){
            mView.showPragress();
        }
        addDisposable(mModel.regiser(username,passworld)
                .subscribe(new HttpResultSuccessSubscriber<RegisterResponse>() {
            @Override
            public void _onSuccess(RegisterResponse registerResponse) {
                if(mView != null) {
                    mView.dissPragress();
                    if (registerResponse != null) {
                        if(registerResponse.getErrorCode() == 0){
                            mView.showToast(R.string.register_success);
                            mView.registerSuccess();
                            return;
                        }
                    }
                    mView.registerError(App.getContext().getResources().getString(R.string.register_failure));
                }
            }
        }, new HttpResultFailureSubscriber() {
            @Override
            public void _onFailure(Throwable t) {
                if(mView != null){
                    mView.dissPragress();
                    mView.registerError(t.toString());
                }
            }
        }));
    }
    /*
     * @Description : 数据校验
     * @Params :
     * @Author : daiw
     * @Date : 2018/12/15
     */
    public void checkData(String userName,String passWord,String rePassword){
        if(mView == null){
            return;
        }
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
            mView.showToast(R.string.the_account_or_password_cannot_be_empty);
            return;
        }
        if(TextUtils.isEmpty(rePassword)){
            mView.showToast(R.string.affirm_password_unable);
            return;
        }
        if(! passWord.equals(rePassword)){
            mView.showToast(R.string.the_passwords_entered_are_inconsistent);
            return;
        }
        //请求注册
        regiser(userName,passWord);
    }
}
