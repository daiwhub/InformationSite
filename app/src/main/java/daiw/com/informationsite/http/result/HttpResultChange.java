package daiw.com.informationsite.http.result;

import com.google.gson.Gson;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/****************************
 * 类描述：将retrofit返回的String转换成对应的实体
 *
 * @author: daiw
 * @time: 2018/11/23  12:19 AM
 *
 *         ***************************
 */
public class HttpResultChange {

    public static final  <R> Observable<R> getHttpResultChange(Observable<JSONObject> observable, final Class<R> cls) {
        Observable<R> observable1 = observable.flatMap(new Function<JSONObject, ObservableSource<R>>() {
            @Override
            public ObservableSource<R> apply(JSONObject jsonObject) throws Exception {
                final R r = new Gson().fromJson(jsonObject.toString(), cls);
                if (r == null) {
                    return null;
                }
                return Observable.create(new ObservableOnSubscribe<R>() {
                    @Override
                    public void subscribe(ObservableEmitter<R> emitter) throws Exception {
                        emitter.onNext(r);
                    }
                });
            }
        });
        return observable1;
    }
}
