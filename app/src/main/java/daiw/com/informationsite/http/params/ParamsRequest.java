package daiw.com.informationsite.http.params;

import java.util.HashMap;

/****************************
 * 类描述：组装参数
 *
 * @author: daiw
 * @time: 2018/12/15  12:29 AM
 *
 *         ***************************
 */
public class ParamsRequest {

    private ParamsRequest(){}

    private static class ParamsRequestInstance {
        private static final ParamsRequest INSTANCE = new ParamsRequest();
    }

    public static ParamsRequest getInstance(){
        return ParamsRequestInstance.INSTANCE;
    }
     /*
      * @Description : 登录
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    public HashMap<String, Object> getLoginRequestParams(String username, String password){
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        return params;
    }
     /*
      * @Description : 注册
      * @Params :
      * @Author : daiw
      * @Date : 2018/12/15
      */
    public HashMap<String,Object> getRegisterRequestParams(String userName,String password){
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        params.put("repassword", password);

        return params;
    }
}
