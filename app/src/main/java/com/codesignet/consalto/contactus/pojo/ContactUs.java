package com.codesignet.consalto.contactus.pojo;

import java.io.Serializable;

/**
 * Created by Aya on 03/06/2018.
 */

public class ContactUs implements Serializable{
    String firstName;
    String lastName;
    String phone;
    String email;
    String msg;

    public ContactUs(String firstName, String lastName, String phone, String email, String msg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.msg = msg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
