package daiw.com.informationsite.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import daiw.com.informationsite.R;
import daiw.com.informationsite.manager.AppManager;
import daiw.com.informationsite.utils.StatusBarUtil;
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

    private LinearLayout mRootLinearLayout;

    private Toolbar mToolbar;

    private int contentView = 0;

    public BaseActivity(int contentView) {
        this.contentView = contentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogoutUtils.d(TAG,"topactivity-->"+this.getClass().getCanonicalName());
        AppManager.getInstance().addActivity(this);

//        setContentView(contentView);
        initLayout();

        initView();
        initToolbar();
    }

    private void initLayout(){
        if(contentView != 0) {
            mRootLinearLayout = new LinearLayout(this);
            mRootLinearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mRootLinearLayout.setLayoutParams(lp);

            ConstraintLayout childView = (ConstraintLayout) LayoutInflater.from(this).inflate(contentView, null);
            ConstraintLayout.LayoutParams childLp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
            childView.setLayoutParams(childLp);

            LinearLayout toolView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null);
            LinearLayout.LayoutParams toolbarLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            toolView.setLayoutParams(toolbarLp);

            mToolbar = toolView.findViewById(R.id.login_toolbar);

            mRootLinearLayout.addView(toolView);
            mRootLinearLayout.addView(childView);

            setContentView(mRootLinearLayout);
        }
    }

    /*
     * @Description : 初始化ToolBar
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/26
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initToolbar() {
        //导航栏沉浸式
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    protected abstract void initView();

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

    public void showShortToast(String message){
        ToastUtil.show(this,message);
    }
    public void showShortToast(int message){
        ToastUtil.show(this,message);
    }
    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }
}
