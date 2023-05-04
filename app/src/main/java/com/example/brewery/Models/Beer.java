package com.example.brewery.Models;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

public class Beer {
    @SerializedName("idBeer")
    public int IdBeer;

    @SerializedName("nameBeer")
    public String NameBeer;

    @SerializedName("productionTime")
    public String ProductionTime;

    @SerializedName("term")
    public String Term;

    @SerializedName("beerTypeId")
    public int BeerTypeId;

    @SerializedName("isDeleted")
    public Boolean IsDeleted;

    public int getIdBeer() {
        return IdBeer;
    }

    public void setIdBeer(int idBeer) {
        IdBeer = idBeer;
    }

    public String getNameBeer() {
        return NameBeer;
    }

    public void setNameBeer(String nameBeer) {
        NameBeer = nameBeer;
    }

    public String getProductionTime() {
        return ProductionTime;
    }

    public void setProductionTime(String productionTime) {
        ProductionTime = productionTime;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public int getBeerTypeId() {
        return BeerTypeId;
    }

    public void setBeerTypeId(int beerTypeId) {
        BeerTypeId = beerTypeId;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
