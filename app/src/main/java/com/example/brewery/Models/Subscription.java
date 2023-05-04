package com.example.brewery.Models;

public class Subscription {
    public int IdSubscription;

    public String NameSubscription;

    public Boolean IsDeleted;

    public int getIdSubscription() {
        return IdSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        IdSubscription = idSubscription;
    }

    public String getNameSubscription() {
        return NameSubscription;
    }

    public void setNameSubscription(String nameSubscription) {
        NameSubscription = nameSubscription;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}
