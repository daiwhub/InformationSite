package daiw.com.informationsite.mvp.contract.register;

import daiw.com.informationsite.bean.RegisterResponse;
import daiw.com.informationsite.interf.mvp.IModel;
import daiw.com.informationsite.interf.mvp.IView;
import io.reactivex.Observable;

/****************************
 * 类描述：注册
 *
 * @author: daiw
 * @time: 2018/12/15  12:15 AM
 *
 *         ***************************
 */
public interface IRegisterContract {

    interface IRegisterView extends IView{
        void registerSuccess();
        void registerError(String message);
    }

    interface IRegisterModel extends IModel{
        Observable<RegisterResponse> regiser(String username,String password);
    }

}
