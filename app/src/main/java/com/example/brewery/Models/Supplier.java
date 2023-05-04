package com.example.brewery.Models;

public class Supplier {
    public int IdSupplier;

    public String NameSupplier;

    public String PhoneSupplier;

    public String AddressSupplier;

    public Boolean IsDeleted;

    public int getIdSupplier() {
        return IdSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        IdSupplier = idSupplier;
    }

    public String getNameSupplier() {
        return NameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        NameSupplier = nameSupplier;
    }

    public String getPhoneSupplier() {
        return PhoneSupplier;
    }

    public void setPhoneSupplier(String phoneSupplier) {
        PhoneSupplier = phoneSupplier;
    }

    public String getAddressSupplier() {
        return AddressSupplier;
    }

    public void setAddressSupplier(String addressSupplier) {
        AddressSupplier = addressSupplier;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
