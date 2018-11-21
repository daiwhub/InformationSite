package daiw.com.informationsite.http.result;

import io.reactivex.functions.Consumer;

/****************************
 * 类描述：接口请求失败回调
 *
 * @author: daiw
 * @time: 2018/11/21  12:15 AM
 *
 *         ***************************
 */
public abstract class HttpResultFailureSubscriber implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) {
        _onFailure(throwable);
    }

    public abstract void _onFailure(Throwable t);

}
