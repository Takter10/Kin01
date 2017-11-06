package com.example.mrpassword.kin01;

/**
 * Created by TnKstudio on 6/11/2560.
 */

public class TypeF {
    private String Name, TID;

    public TypeF() {
    }

    public TypeF(String name, String TID) {
        Name = name;
        this.TID = TID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }
}
