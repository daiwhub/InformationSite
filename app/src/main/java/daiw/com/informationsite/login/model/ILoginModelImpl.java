package daiw.com.informationsite.login.model;

import java.util.HashMap;

import daiw.com.informationsite.login.ApiService;
import daiw.com.informationsite.utils.download.IDownLoadCallback;
import daiw.com.informationsite.utils.log.LogoutUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  11:46 AM
 *
 *         ***************************
 */
public class ILoginModelImpl implements ILoginModel{

    @Override
    public void loadLogin(ModelLogin modelLogin,ILoginModelCallback callback) {

        //进行网络请求
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        HashMap<String,Object> params = new HashMap<>();
        if(modelLogin == null){
            LogoutUtils.d("请求参数异常");
            return;
        }
        params.put("username",modelLogin.getUserName());
        params.put("password",modelLogin.getPassWorld());

        apiService.sendRequestLogin(params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogoutUtils.d("登录成功 : " + response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogoutUtils.d("登录失败 : " + t.toString());
            }
        });
    }
}
