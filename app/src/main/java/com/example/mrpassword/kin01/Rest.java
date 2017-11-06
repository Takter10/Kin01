package com.example.mrpassword.kin01;

/**
 * Created by TnKstudio on 6/11/2560.
 */

public class Rest {
    private String Name, PFood, RID;

    public Rest() {
    }

    public Rest(String name, String PFood, String RID) {
        Name = name;
        this.PFood = PFood;
        this.RID = RID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPFood() {
        return PFood;
    }

    public void setPFood(String PFood) {
        this.PFood = PFood;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }
}
