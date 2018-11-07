package daiw.com.informationsite.interf;

/****************************
 * 类描述：动态权限申请回调
 *
 * @author: daiw
 * @time: 2018/11/7  6:55 PM
 *
 *         ***************************
 */
public interface PermissionsRequestInterf {
    void onSuccess();
    void onError(int errorCode);

}
