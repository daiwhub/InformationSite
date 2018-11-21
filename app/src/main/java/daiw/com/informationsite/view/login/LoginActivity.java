package daiw.com.informationsite.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import daiw.com.informationsite.R;
import daiw.com.informationsite.base.MvpBaseActivity;
import daiw.com.informationsite.interf.login.ILoginContract;
import daiw.com.informationsite.mvp.percenter.LoginPercenter;

public class LoginActivity extends MvpBaseActivity<LoginPercenter> implements ILoginContract.ILoginView {

    public LoginActivity(int contentView) {
        super(R.layout.activity_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LoginPercenter createPercenter() {
        return new LoginPercenter(this);
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showToast(int message) {

    }
}
