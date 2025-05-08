package com.muyoma.cld_gallery.user.UserAuthModels;

public class UserLoginResponse {
    public String mail;
    public String token;
    public UserLoginResponse(String mail, String token) {
        this.mail = mail;
        this.token = token;
    }
}
