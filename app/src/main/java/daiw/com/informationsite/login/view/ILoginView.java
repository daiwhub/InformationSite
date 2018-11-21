package daiw.com.informationsite.login.view;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  11:39 AM
 *
 *         ***************************
 */
public interface ILoginView {

    void loginSuccess();
    void loginFailure(int errorCode);

}
