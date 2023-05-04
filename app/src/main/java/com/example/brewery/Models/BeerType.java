package com.example.brewery.Models;

import com.google.gson.annotations.SerializedName;

public class BeerType {
    @SerializedName("idBeerType")
    public int IdBeerType;

    @SerializedName("nameBeerType")
    public String NameBeerType;

    @SerializedName("isDeleted")
    public Boolean IsDeleted;

    public int getIdBeerType() {
        return IdBeerType;
    }

    public void setIdBeerType(int idBeerType) {
        IdBeerType = idBeerType;
    }

    public String getNameBeerType() {
        return NameBeerType;
    }

    public void setNameBeerType(String nameBeerType) {
        NameBeerType = nameBeerType;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
