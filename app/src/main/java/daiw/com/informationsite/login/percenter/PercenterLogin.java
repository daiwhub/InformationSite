package daiw.com.informationsite.login.percenter;

import java.lang.ref.WeakReference;

import daiw.com.informationsite.login.model.ILoginModel;
import daiw.com.informationsite.login.model.ILoginModelImpl;
import daiw.com.informationsite.login.model.ModelLogin;
import daiw.com.informationsite.login.view.ILoginView;
import daiw.com.informationsite.utils.log.LogoutUtils;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  12:08 PM
 *
 *         ***************************
 */
public class PercenterLogin<T extends ILoginView> {

    private WeakReference<T> mView;

    private ILoginModel iLoginModel = new ILoginModelImpl();

    public PercenterLogin(T view){
        mView = new WeakReference<>(view);
    }

    public void fetch(ModelLogin modelLogin){
        if(modelLogin == null){
            LogoutUtils.d("请输入用户名和密码");
            return;
        }

        if(mView.get() != null && iLoginModel != null){
            iLoginModel.loadLogin(modelLogin,new ILoginModel.ILoginModelCallback() {
                @Override
                public void onComplete(String string) {
                    LogoutUtils.d("请求成功");
                }
            });
        }
    }

}
