package com.example.mrpassword.kin01;

/**
 * Created by TnKstudio on 4/11/2560.
 */

public class Food {
    private String FID,Name,Pic ;

    public Food(){

    }

    public Food(String FID, String name, String pic) {
        this.FID = FID;
        Name = name;
        Pic = pic;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }
}
