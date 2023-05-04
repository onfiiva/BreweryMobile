package com.example.brewery.Models;

public class Ingridient {
    public int IdIngridient;

    public String NameIngridient;

    public int IngridientTypeId;

    public int AdminId;

    public int SupplierId;

    public Boolean IsDeleted;

    public int getIdIngridient() {
        return IdIngridient;
    }

    public void setIdIngridient(int idIngridient) {
        IdIngridient = idIngridient;
    }

    public String getNameIngridient() {
        return NameIngridient;
    }

    public void setNameIngridient(String nameIngridient) {
        NameIngridient = nameIngridient;
    }

    public int getIngridientTypeId() {
        return IngridientTypeId;
    }

    public void setIngridientTypeId(int ingridientTypeId) {
        IngridientTypeId = ingridientTypeId;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
