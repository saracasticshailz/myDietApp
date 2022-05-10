package com.d4h.hp.diet4happlication.AllDataModels;

public class LoginModel {
    public String getP_id() {
        return user_id;
    }

    public void setP_id(String p_id) {
        this.user_id = p_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LoginModel(String p_id, String status, String name, String male, String email, String contact, String city, String state) {
        this.user_id = p_id;
        this.status = status;
        this.name = name;
        this.male = male;
        this.email = email;
        this.contact = contact;
        this.city = city;
        this.state = state;
    }

    String user_id,status,name,male,email,contact,city,state;
}
