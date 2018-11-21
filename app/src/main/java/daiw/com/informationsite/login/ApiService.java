package daiw.com.informationsite.login;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  11:51 AM
 *
 *         ***************************
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    Call<String> sendRequestLogin(@FieldMap Map<String,Object> params);

}
