package daiw.com.informationsite.view.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import daiw.com.informationsite.R;
import daiw.com.informationsite.base.MvpBaseActivity;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.mvp.contract.login.ILoginContract;
import daiw.com.informationsite.mvp.percenter.LoginPercenter;
import daiw.com.informationsite.view.custom.edittext.CustomEditTextLogin;

public class LoginActivity extends MvpBaseActivity<LoginPercenter>
        implements ILoginContract.ILoginView , View.OnClickListener {


    private Button loginBtn;
    private Button registerBtn;

    private CustomEditTextLogin mUserNameEdit;
    private CustomEditTextLogin mPassWordEdit;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    public void initView() {
        //登录
        loginBtn = findViewById(R.id.ac_login_to_login_btn);
        //注册
        registerBtn = findViewById(R.id.ac_login_to_register_btn);
        //输入账号
        mUserNameEdit = findViewById(R.id.ac_login_username_custom);
        //输入密码
        mPassWordEdit = findViewById(R.id.ac_login_password_custom);
    }

    private void setListener() {
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public LoginPercenter createPercenter() {
        return new LoginPercenter(this);
    }

    @Override
    public void showToast(String message) {
        showShortToast(message);
    }

    @Override
    public void showToast(int message) {
        showShortToast(message);
    }

    @Override
    public void showPragress() {

    }

    @Override
    public void dissPragress() {

    }

    @Override
    public void loginSuccess() {
        finish();
    }

    @Override
    public void loginError(String errorMessage) {
        showToast(errorMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_login_to_login_btn:
                //登录
                if (mPercenter == null) {
                    return;
                }
                String userName = mUserNameEdit.getEditText();
                String passWord = mPassWordEdit.getEditText();
                mPercenter.checkData(userName, passWord);
                break;
            case R.id.ac_login_to_register_btn:
                //注册
                StartActivityManager.startRegisterActivity(LoginActivity.this);
                break;
        }
    }
}
