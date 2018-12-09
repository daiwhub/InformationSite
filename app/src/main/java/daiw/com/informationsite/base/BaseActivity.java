package daiw.com.informationsite.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import daiw.com.informationsite.utils.ToastUtil;
import daiw.com.informationsite.utils.log.LogoutUtils;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/19  11:51 PM
 *
 *         ***************************
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = BaseActivity.class.getCanonicalName();

    private int contentView;

    public BaseActivity(int contentView) {
        this.contentView = contentView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogoutUtils.d(TAG,"topactivity-->"+this.getClass().getCanonicalName());

        setContentView(contentView);
        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
     /*
      * @Description : 初始化ToolBar
      * @Params :
      * @Author : daiw
      * @Date : 2018/11/26
      */
    public abstract void initToolbar();

    public void showShortToast(String message){
        ToastUtil.show(this,message);
    }
    public void showShortToast(int message){
        ToastUtil.show(this,message);
    }
}
