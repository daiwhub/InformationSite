package daiw.com.informationsite.view.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import daiw.com.informationsite.R;
import daiw.com.informationsite.base.MvpBaseActivity;
import daiw.com.informationsite.manager.StartActivityManager;
import daiw.com.informationsite.mvp.contract.register.IRegisterContract;
import daiw.com.informationsite.mvp.percenter.register.RegisterPercenter;
import daiw.com.informationsite.view.custom.edittext.CustomEditTextLogin;

/****************************
 * 类描述：注册
 *
 * @author: daiw
 * @time: 2018/12/14  11:51 PM
 *
 *         ***************************
 */
public class RegisterActivity extends MvpBaseActivity<RegisterPercenter>
        implements IRegisterContract.IRegisterView, View.OnClickListener {

    private CustomEditTextLogin mRegisterUsernameEdit;
    private CustomEditTextLogin mRegisterPasswordEdit;
    private CustomEditTextLogin mRegisterRePasswordEdit;
    private Button mRegisterBtn;

    public RegisterActivity() {
        super(R.layout.activity_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        //设置标题
        setToolbarTitle(R.string.register);
        //邮箱或手机号
        mRegisterUsernameEdit = findViewById(R.id.ac_register_username_custom);
        //密码
        mRegisterPasswordEdit = findViewById(R.id.ac_register_password_custom);
        //确认密码
        mRegisterRePasswordEdit = findViewById(R.id.ac_register_password_custom_again);
        //注册
        mRegisterBtn = findViewById(R.id.ac_register_btn);

        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public RegisterPercenter createPercenter() {
        return new RegisterPercenter(this);
    }

    @Override
    public void onClick(View v) {
        if(mPercenter == null){
            return;
        }
        String username = mRegisterUsernameEdit.getEditText();
        String password = mRegisterPasswordEdit.getEditText();
        String repassword = mRegisterRePasswordEdit.getEditText();
        //校验数据
        mPercenter.checkData(username,password,repassword);
    }

    @Override
    public void registerSuccess() {
        StartActivityManager.INSTANCE.startManiActivity(RegisterActivity.this);
        finish();
    }

    @Override
    public void registerError(String message) {
        showShortToast(message);
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
}
