package daiw.com.informationsite.base;

import daiw.com.informationsite.interf.mvp.IModel;
import daiw.com.informationsite.interf.mvp.IPercenter;
import daiw.com.informationsite.interf.mvp.IView;
import daiw.com.informationsite.mvp.contract.login.ILoginContract;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/19  11:58 PM
 *
 *         ***************************
 */
public class BasePercenter<V extends IView,M extends IModel> implements IPercenter {

    private CompositeDisposable mCompositeDisposable;

    protected V mView;
    protected M mModel;

    public BasePercenter(V view,M model){
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {
        unDisposable();
        if(mView != null){
            mView = null;
        }
        if(mModel != null){
            mModel.onDestroy();
            mModel = null;
        }
        mCompositeDisposable = null;
    }

    protected void addDisposable(Disposable disposable){
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void unDisposable(){
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
    }

}
