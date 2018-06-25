package com.codesignet.consalto.login.data_access_layer.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hagar on 30/05/2018.
 */

public class LoginRequestPojo implements Serializable {
    @SerializedName("mail")
    private String mail;

    @SerializedName("password")
    private String password;

    public LoginRequestPojo(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
