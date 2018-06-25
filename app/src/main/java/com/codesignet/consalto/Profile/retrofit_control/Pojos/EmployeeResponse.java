package com.codesignet.consalto.Profile.retrofit_control.Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aya on 6/1/2018.
 */

public class EmployeeResponse {

    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage = new ResponseMessage();
    @SerializedName("employeePojo")
    @Expose
    private Employee employeePojo = new Employee();

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Employee getEmployeePojo() {
        return employeePojo;
    }

    public void setEmployeePojo(Employee employeePojo) {
        this.employeePojo = employeePojo;
    }
}
