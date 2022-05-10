package com.d4h.hp.diet4happlication.AllDataModels;

public class WorkCompletedModel {

    String strActivity,strDate,strRepetation;

    public WorkCompletedModel(String strActivity, String strDate, String strRepetation) {
        this.strActivity = strActivity;
        this.strDate = strDate;
        this.strRepetation = strRepetation;
    }

    public WorkCompletedModel() {
    }

    public String getStrActivity() {
        return strActivity;
    }

    public void setStrActivity(String strActivity) {
        this.strActivity = strActivity;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrRepetation() {
        return strRepetation;
    }

    public void setStrRepetation(String strRepetation) {
        this.strRepetation = strRepetation;
    }
}
