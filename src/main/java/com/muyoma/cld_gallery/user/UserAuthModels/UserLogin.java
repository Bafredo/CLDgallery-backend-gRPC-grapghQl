package com.muyoma.cld_gallery.user.UserAuthModels;

public class UserLogin {
    public String mail;
    public String password;

    public UserLogin(String email, String password) {
        this.mail = email;
        this.password = password;
    }
}
