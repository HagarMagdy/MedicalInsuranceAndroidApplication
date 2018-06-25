package com.codesignet.consalto.forget_password.pojo;

import java.io.Serializable;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class ResponseMessage implements Serializable {

   private String message;
   private Boolean status;
   private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
