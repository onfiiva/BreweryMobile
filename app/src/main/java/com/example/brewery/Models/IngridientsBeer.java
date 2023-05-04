package com.example.brewery.Models;

public class IngridientsBeer {
    public int IdUsersBeer;

    public int IngridientId;

    public int BeerId;

    public Boolean IsDeleted;

    public int getIdUsersBeer() {
        return IdUsersBeer;
    }

    public void setIdUsersBeer(int idUsersBeer) {
        IdUsersBeer = idUsersBeer;
    }

    public int getIngridientId() {
        return IngridientId;
    }

    public void setIngridientId(int ingridientId) {
        IngridientId = ingridientId;
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
