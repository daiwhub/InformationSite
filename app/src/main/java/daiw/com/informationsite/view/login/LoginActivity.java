package daiw.com.informationsite.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import daiw.com.informationsite.R;
import daiw.com.informationsite.base.MvpBaseActivity;
import daiw.com.informationsite.interf.login.ILoginContract;
import daiw.com.informationsite.mvp.percenter.LoginPercenter;

public class LoginActivity extends MvpBaseActivity<LoginPercenter> implements ILoginContract.ILoginView {

    private Button loginBtn;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        loginBtn = findViewById(R.id.login_btn);
    }

    private void setListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPercenter.login("daiwei","123456");
            }
        });
    }

    @Override
    public LoginPercenter createPercenter() {
        return new LoginPercenter(this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int message) {

    }

    @Override
    public void loginError(String errorMessage) {
        Toast.makeText(this, errorMessage + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dissmissDialog() {

    }
}
