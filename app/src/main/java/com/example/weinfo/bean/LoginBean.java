package com.example.weinfo.bean;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/9   18:16
 **/
public class LoginBean {

    /**
     * code : 200
     * message : 认证成功
     * user : {"userid":"xts","password":"xts123456","name":"7kF9pW","headerpic":"http://47.110.151.50/p6image/img/per.jpg"}
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6Inh0czEyMzQ1NiIsImV4cCI6MTU4OTExMDQ2NywidXNlcm5hbWUiOiJ4dHMifQ.0OII7j3FTr2QNs9R0LgSdQzFUhRSzG1u4L6Rna3rQCE
     */

    private String code;
    private String message;
    private UserBean user;
    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * userid : xts
         * password : xts123456
         * name : 7kF9pW
         * headerpic : http://47.110.151.50/p6image/img/per.jpg
         */

        private String userid;
        private String password;
        private String name;
        private String headerpic;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeaderpic() {
            return headerpic;
        }

        public void setHeaderpic(String headerpic) {
            this.headerpic = headerpic;
        }
    }
}
