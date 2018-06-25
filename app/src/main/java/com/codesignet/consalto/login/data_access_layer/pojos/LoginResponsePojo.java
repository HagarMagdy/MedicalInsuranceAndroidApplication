package com.codesignet.consalto.login.data_access_layer.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hagar on 30/05/2018.
 */

public class LoginResponsePojo implements Serializable {

    @SerializedName("status")

    private Boolean status;

    @SerializedName("message")

    private String message;

    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private int id;

    public LoginResponsePojo(Boolean status, String message, String error, int id) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }
}
