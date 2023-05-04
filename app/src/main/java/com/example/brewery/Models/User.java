package com.example.brewery.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("idUser")
    public int IdUser;

    @SerializedName("userPhone")
    public String UserPhone;

    @SerializedName("loginUser")
    public String LoginUser;

    @SerializedName("passwordUser")
    public String PasswordUser;

    @SerializedName("subscriptionId")
    public int SubscriptionId;

    @SerializedName("passwordSalt")
    public String PasswordSalt;

    @SerializedName("isDeleted")
    public Boolean IsDeleted;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(String loginUser) {
        LoginUser = loginUser;
    }

    public String getPasswordUser() {
        return PasswordUser;
    }

    public void setPasswordUser(String passwordUser) {
        PasswordUser = passwordUser;
    }

    public int getSubscriptionId() {
        return SubscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        SubscriptionId = subscriptionId;
    }

    public String getPasswordSalt() {
        return PasswordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        PasswordSalt = passwordSalt;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
