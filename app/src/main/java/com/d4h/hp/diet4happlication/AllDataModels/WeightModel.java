package com.d4h.hp.diet4happlication.AllDataModels;

public class WeightModel {
    String Wdate,Wweight,Wbmi;

    public WeightModel() {

    }

    public WeightModel( String Wdate,String Wweight,String Wbmi) {
        this.Wdate=Wdate;
        this.Wweight=Wweight;
        this.Wdate=Wbmi;
    }

    public String getWdate() {
        return Wdate;
    }

    public void setWdate(String wdate) {
        Wdate = wdate;
    }

    public String getWweight() {
        return Wweight;
    }

    public void setWweight(String wweight) {
        Wweight = wweight;
    }

    public String getWbmi() {
        return Wbmi;
    }

    public void setWbmi(String wbmi) {
        Wbmi = wbmi;
    }
}
