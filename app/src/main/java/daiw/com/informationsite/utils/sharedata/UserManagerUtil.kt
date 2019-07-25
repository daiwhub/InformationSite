package daiw.com.informationsite.utils.sharedata

import daiw.com.informationsite.bean.LoginResponse
import daiw.com.informationsite.bean.UserInfo

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2019-07-26  00:26
 *
 *         ***************************
 */
class UserManagerUtil {
    companion object{
        /**
         * 登录后存储登录信息
         */
        fun loginAfterCoypUserInfo(loginResponse: LoginResponse): UserInfo {
            var usetInfo = UserInfo()
            if(loginResponse.data != null) {
                usetInfo.email = loginResponse.data.email
                usetInfo.icon = loginResponse.data.icon
                usetInfo.token = loginResponse.data.token
                usetInfo.type = loginResponse.data.type
                usetInfo.userName = loginResponse.data.username
                usetInfo.nickname = loginResponse.data.nickname
            }
            return usetInfo
        }
    }
}