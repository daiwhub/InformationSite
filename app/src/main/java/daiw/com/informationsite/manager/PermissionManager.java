package daiw.com.informationsite.manager;




import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;

import daiw.com.informationsite.interf.PermissionsRequestInterf;
import daiw.com.informationsite.utils.log.LogoutUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/****************************
 * 类描述：动态权限管理
 *
 * @author: daiw
 * @time: 2018/11/7  6:52 PM
 *
 *         ***************************
 */
public class PermissionManager {
    private static final String TAG = PermissionManager.class.getCanonicalName();
    /**
     * 用户拒绝权限获取
     */
    public static final int PERMISSION_REFUSE = 397;

    /**
     * 获取权限出错
     */
    public static final int PERMISSION_ERROR = 398;

    private static PermissionManager permissionManager;
    public static PermissionManager getInstance(){
        if(permissionManager == null){
            synchronized (PermissionManager.class){
                if(permissionManager == null){
                    permissionManager = new PermissionManager();
                }
            }
        }
        return permissionManager;
    }

    public void triggerPermissionRequest(final FragmentActivity activity,
                                         final PermissionsRequestInterf interf,
                                         String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean){
                            //申请成功
                            LogoutUtils.d(TAG,"动态权限申请成功！！！");
                            if(interf != null){
                                interf.onSuccess();
                            }
                        }else {
                            //用户拒绝
                            LogoutUtils.d(TAG,"动态权限申请拒绝！！！");
                            if(interf != null){
                                interf.onError(PERMISSION_REFUSE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //申请失败
                        LogoutUtils.d(TAG,"动态权限申请失败！！！");
                        if(interf != null){
                            interf.onError(PERMISSION_ERROR);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
