/** */
package demo.user;

/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:50
 */
public class userPojo {
    /**
     * 用户id
     */
    private int id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String userName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 确认密码
     **/
    private String final_password;
    /**
     * 用户登陆token
     **/
    private String loginToken;


    public String getLoginToken() {
        return loginToken;
    }


    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }


    public String getFinal_password() {
        return final_password;
    }


    public void setFinal_password(String final_password) {
        this.final_password = final_password;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
