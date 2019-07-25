package daiw.com.informationsite.view.login

import android.os.Bundle
import android.view.View
import android.widget.Button

import daiw.com.informationsite.R
import daiw.com.informationsite.base.MvpBaseActivity
import daiw.com.informationsite.manager.StartActivityManager
import daiw.com.informationsite.mvp.contract.login.ILoginContract
import daiw.com.informationsite.mvp.percenter.LoginPercenter
import daiw.com.informationsite.view.custom.edittext.CustomEditTextLogin

class LoginActivity : MvpBaseActivity<LoginPercenter>(R.layout.activity_login), ILoginContract.ILoginView, View.OnClickListener {


    private var loginBtn: Button? = null
    private var registerBtn: Button? = null

    private var mUserNameEdit: CustomEditTextLogin? = null
    private var mPassWordEdit: CustomEditTextLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListener()
    }

    public override fun initView() {
        //登录
        loginBtn = findViewById(R.id.ac_login_to_login_btn)
        //注册
        registerBtn = findViewById(R.id.ac_login_to_register_btn)
        //输入账号
        mUserNameEdit = findViewById(R.id.ac_login_username_custom)
        //输入密码
        mPassWordEdit = findViewById(R.id.ac_login_password_custom)
    }

    private fun setListener() {
        loginBtn!!.setOnClickListener(this)
        registerBtn!!.setOnClickListener(this)
    }

    override fun createPercenter(): LoginPercenter {
        return LoginPercenter(this)
    }

    override fun showToast(message: String) {
        showShortToast(message)
    }

    override fun showToast(message: Int) {
        showShortToast(message)
    }

    override fun showPragress() {

    }

    override fun dissPragress() {

    }

    override fun loginSuccess() {
        finish()
    }

    override fun loginError(errorMessage: String) {
        showToast(errorMessage)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ac_login_to_login_btn -> {
                //登录
                if (mPercenter == null) {
                    return
                }
                val userName = mUserNameEdit!!.editText
                val passWord = mPassWordEdit!!.editText
                mPercenter.checkData(userName, passWord)
            }
            R.id.ac_login_to_register_btn ->
                //注册
                StartActivityManager.startRegisterActivity(this@LoginActivity)
        }
    }
}
