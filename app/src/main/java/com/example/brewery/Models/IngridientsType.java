package com.example.brewery.Models;

public class IngridientsType {
    public int IdIngridientType;

    public String NameIngridientType;

    public Boolean IsDeleted;

    public int getIdIngridientType() {
        return IdIngridientType;
    }

    public void setIdIngridientType(int idIngridientType) {
        IdIngridientType = idIngridientType;
    }

    public String getNameIngridientType() {
        return NameIngridientType;
    }

    public void setNameIngridientType(String nameIngridientType) {
        NameIngridientType = nameIngridientType;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
