package daiw.com.informationsite.mvp.percenter

import android.text.TextUtils

import daiw.com.informationsite.App
import daiw.com.informationsite.R
import daiw.com.informationsite.api.constans.ConfigConstans
import daiw.com.informationsite.base.BasePercenter
import daiw.com.informationsite.bean.LoginResponse
import daiw.com.informationsite.bean.UserInfo
import daiw.com.informationsite.http.result.HttpResultFailureSubscriber
import daiw.com.informationsite.http.result.HttpResultSuccessSubscriber
import daiw.com.informationsite.mvp.contract.login.ILoginContract
import daiw.com.informationsite.mvp.model.LoginModel
import daiw.com.informationsite.utils.sharedata.ShareData
import daiw.com.informationsite.utils.sharedata.UserManagerUtil

/****************************
 * 类描述：登录请求
 *
 * @author: daiw
 * @time: 2018/11/20  11:52 PM
 *
 * ***************************
 */
class LoginPercenter(view: ILoginContract.ILoginView) : BasePercenter<ILoginContract.ILoginView, ILoginContract.ILoginModel>(view, LoginModel()) {
    /*
      * @Description : 请求登录
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    fun login(username: String, password: String) {
        //开始加载动画
        if (mView != null) {
            mView.showPragress()
        }
        addDisposable(mModel.login(username, password).subscribe(object : HttpResultSuccessSubscriber<LoginResponse>() {

            override fun _onSuccess(loginResponse: LoginResponse?) {
                if (mView != null) {
                    mView.dissPragress()
                    if (loginResponse != null) {
                        if (loginResponse.errorCode == 0) {
                            mView.showToast(App.getContext().resources.getString(R.string.login_success))
                            //缓存登录成功信息
                            val userInfo = UserManagerUtil.loginAfterCoypUserInfo(loginResponse)
                            userInfo.isLogin = true
                            //缓存登录标识
                            ShareData.getInstance().saveBooleanValue(ConfigConstans.USER_FLAG_ISLOGIN,userInfo.isLogin)

                            mView.loginSuccess()
                            return
                        }
                    }
                    mView.loginError(App.getContext().resources.getString(R.string.login_failure))
                }
            }
        }, object : HttpResultFailureSubscriber() {
            override fun _onFailure(t: Throwable) {
                if (mView != null) {
                    mView.dissPragress()
                    mView.loginError(t.message)
                }
            }
        }))
    }

    /*
      * @Description : 数据校验
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/9
      */
    fun checkData(userName: String, passWord: String) {
        if (mView == null) {
            return
        }
        if (TextUtils.isEmpty(userName)) {
            mView.showToast(R.string.the_account_cannot_be_empty)
            return
        }
        if (TextUtils.isEmpty(passWord)) {
            mView.showToast(R.string.the_password_cannot_be_empty)
            return
        }
        //请求登录
        login(userName, passWord)
    }

}
