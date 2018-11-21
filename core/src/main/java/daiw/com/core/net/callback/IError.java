package daiw.com.core.net.callback;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  5:42 PM
 *
 *         ***************************
 */
public interface IError {
    void onError(int code,String message);
}
