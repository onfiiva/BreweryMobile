package com.example.brewery.Models;

public class BreweryBeer {
    public int IdBreweryBeer;

    public int BreweryId;

    public int BeerId;

    public Boolean IsDeleted;

    public int getIdBreweryBeer() {
        return IdBreweryBeer;
    }

    public void setIdBreweryBeer(int idBreweryBeer) {
        IdBreweryBeer = idBreweryBeer;
    }

    public int getBreweryId() {
        return BreweryId;
    }

    public void setBreweryId(int breweryId) {
        BreweryId = breweryId;
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
