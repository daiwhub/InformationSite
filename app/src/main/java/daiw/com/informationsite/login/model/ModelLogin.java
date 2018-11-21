package daiw.com.informationsite.login.model;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/9  11:47 AM
 *
 *         ***************************
 */
public class ModelLogin {

    private String userName;
    private String passWorld;

    public ModelLogin() {
    }

    public ModelLogin(String userName, String passWorld) {
        this.userName = userName;
        this.passWorld = passWorld;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWorld() {
        return passWorld;
    }

    public void setPassWorld(String passWorld) {
        this.passWorld = passWorld;
    }
}
