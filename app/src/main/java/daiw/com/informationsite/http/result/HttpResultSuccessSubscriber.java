package daiw.com.informationsite.http.result;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.functions.Consumer;

/****************************
 * 类描述：接口请求成功回调
 *
 * @author: daiw
 * @time: 2018/11/21  12:15 AM
 *
 *         ***************************
 */
public abstract class HttpResultSuccessSubscriber<T> implements Consumer<T> {

    @Override
    public void accept(T t) throws Exception {
        _onSuccess(t);
    }

    public abstract void _onSuccess(T t);

}
