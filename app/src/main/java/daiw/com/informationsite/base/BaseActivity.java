package daiw.com.informationsite.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import daiw.com.informationsite.R;
import daiw.com.informationsite.manager.AppManager;
import daiw.com.informationsite.utils.StatusBarUtil;
import daiw.com.informationsite.utils.ToastUtil;
import daiw.com.informationsite.utils.log.LogoutUtils;

/****************************
 * 类描述：基类Activity
 *
 * @author: daiw
 * @time: 2018/11/19  11:51 PM
 *
 *         ***************************
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = BaseActivity.class.getCanonicalName();

    private LinearLayout mRootLinearLayout;

    private ConstraintLayout toolView;
    private TextView mToolbarBackTv;
    private TextView mToolbarTitleTv;
    private TextView mToolbarRightTv;

    private boolean isShowToolbar = true;

    private int contentView = 0;

    public BaseActivity(int contentView) {
        this.contentView = contentView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogoutUtils.d(TAG,"topactivity-->"+this.getClass().getCanonicalName());
        AppManager.getInstance().addActivity(this);

        initLayout();

        initView();
        if(isShowToolbar) {
            initToolbar();
        }
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

            if(isShowToolbar) {
                toolView = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null);
                LinearLayout.LayoutParams toolbarLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                toolView.setLayoutParams(toolbarLp);

                //返回
                mToolbarBackTv = toolView.findViewById(R.id.toolbar_back_btn);
                //标题
                mToolbarTitleTv = toolView.findViewById(R.id.toolbar_title_tv);
                //右按钮
                mToolbarRightTv = toolView.findViewById(R.id.toolbar_right_tv);

                mRootLinearLayout.addView(toolView);
            }

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
    public void initToolbar() {
        //导航栏沉浸式
        StatusBarUtil.immersive(this);
        //设置状态栏白底黑字
        StatusBarUtil.darkMode(this,true);
        StatusBarUtil.setPaddingSmart(this, toolView);
        //返回
        mToolbarBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
        //右按钮
        mToolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToolbarRight(v);
            }
        });
    }

     /*
      * @Description : 是否显示标题栏
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/14
      */
    public void isShowToolbar(boolean isShowToolbar){
        this.isShowToolbar = isShowToolbar;
    }
     /*
      * @Description : 设置标题栏
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/13
      */
     public void setToolbarTitle(int resource) {
        if(mToolbarRightTv == null){
            return;
        }
        if(mToolbarTitleTv.getVisibility() != View.VISIBLE){
            mToolbarTitleTv.setVisibility(View.VISIBLE);
        }
        //标题
        mToolbarTitleTv.setText(resource);
    }
     /*
      * @Description : 设置标题栏
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/13
      */
     public void setToolbarTitle(String resource) {
        if(mToolbarRightTv == null){
            return;
        }
        if(mToolbarTitleTv.getVisibility() != View.VISIBLE){
            mToolbarTitleTv.setVisibility(View.VISIBLE);
        }
        //标题
        mToolbarTitleTv.setText(resource);
    }

    protected abstract void initView();
     /*
      * @Description : 功能标题栏右侧按钮（子类需要时重写）
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/13
      */
    public void clickToolbarRight(View v){

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
