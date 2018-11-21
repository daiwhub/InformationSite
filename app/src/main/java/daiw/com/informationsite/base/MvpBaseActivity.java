package daiw.com.informationsite.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import daiw.com.informationsite.interf.mvp.IModel;
import daiw.com.informationsite.interf.mvp.IPercenter;
import daiw.com.informationsite.interf.mvp.IView;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/19  11:52 PM
 *
 *         ***************************
 */
public abstract class MvpBaseActivity<P extends BasePercenter> extends BaseActivity {

    protected P mPercenter;

    public MvpBaseActivity(int contentView){
        super(contentView);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPercenter == null) {
            mPercenter = createPercenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPercenter != null){
            mPercenter.detachView();
        }
    }

    public abstract  P createPercenter();


}
