package com.example.brewery.Models;

import com.google.gson.annotations.SerializedName;

public class BeerCheque {

    @SerializedName("idBeerCheque")
    public int IdBeerCheque;

    @SerializedName("chequeId")
    public int ChequeId;

    @SerializedName("beerId")
    public int BeerId;

    @SerializedName("isDeleted")
    public Boolean IsDeleted;

    public BeerCheque(int chequeId, int beerId, Boolean isDeleted) {
        ChequeId = chequeId;
        BeerId = beerId;
        IsDeleted = isDeleted;
    }

    public int getIdBeerCheque() {
        return IdBeerCheque;
    }

    public void setIdBeerCheque(int idBeerCheque) {
        IdBeerCheque = idBeerCheque;
    }

    public int getChequeId() {
        return ChequeId;
    }

    public void setChequeId(int chequeId) {
        ChequeId = chequeId;
    }

    public int getBeerId() {
        return BeerId;
    }

    public void setBeerId(int beerId) {
        BeerId = beerId;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
