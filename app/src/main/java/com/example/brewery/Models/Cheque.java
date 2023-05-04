package com.example.brewery.Models;

import com.google.gson.annotations.SerializedName;

public class Cheque {
    @SerializedName("idCheque")
    public int IdCheque;

    @SerializedName("userId")
    public int UserId;

    @SerializedName("sum")
    public int Sum;

    @SerializedName("timeOrder")
    public String TimeOrder;

    @SerializedName("isDeleted")
    public Boolean IsDeleted;

    public Cheque(int userId, int sum, String timeOrder, Boolean isDeleted) {
        UserId = userId;
        Sum = sum;
        TimeOrder = timeOrder;
        IsDeleted = isDeleted;
    }

    public void ChequePut(int idCheque, int userId, int sum, String timeOrder, Boolean isDeleted) {
        IdCheque = idCheque;
        UserId = userId;
        Sum = sum;
        TimeOrder = timeOrder;
        IsDeleted = isDeleted;
    }

    public int getIdCheque() {
        return IdCheque;
    }

    public void setIdCheque(int idCheque) {
        IdCheque = idCheque;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getSum() {
        return Sum;
    }

    public void setSum(int sum) {
        Sum = sum;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        TimeOrder = timeOrder;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
