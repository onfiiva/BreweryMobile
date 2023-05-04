package com.example.brewery.Models;

public class Brewery {
    public int IdBrewery;

    public String NameBrewery;

    public String AddressBrewery;

    public Boolean IsDeleted;

    public int getIdBrewery() {
        return IdBrewery;
    }

    public void setIdBrewery(int idBrewery) {
        IdBrewery = idBrewery;
    }

    public String getNameBrewery() {
        return NameBrewery;
    }

    public void setNameBrewery(String nameBrewery) {
        NameBrewery = nameBrewery;
    }

    public String getAddressBrewery() {
        return AddressBrewery;
    }

    public void setAddressBrewery(String addressBrewery) {
        AddressBrewery = addressBrewery;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
