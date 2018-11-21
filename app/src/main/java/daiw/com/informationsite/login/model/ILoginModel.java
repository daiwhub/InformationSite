package daiw.com.informationsite.login.model;

import daiw.com.informationsite.utils.download.IDownLoadCallback;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  11:41 AM
 *
 *         ***************************
 */
public interface ILoginModel {

    void loadLogin(ModelLogin modelLogin,ILoginModelCallback callback);

   public interface ILoginModelCallback {
        void onComplete(String string);
   }

}
