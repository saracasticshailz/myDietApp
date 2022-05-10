package com.d4h.hp.diet4happlication.AllDataModels;

public class WorkSuggestedModel {

    String strActivity,strRepetation;

    public WorkSuggestedModel(String strActivity, String strRepetation) {
        this.strActivity = strActivity;
        this.strRepetation = strRepetation;
    }

    public WorkSuggestedModel() {
    }

    public String getStrActivity() {
        return strActivity;
    }

    public void setStrActivity(String strActivity) {
        this.strActivity = strActivity;
    }

    public String getStrRepetation() {
        return strRepetation;
    }

    public void setStrRepetation(String strRepetation) {
        this.strRepetation = strRepetation;
    }
}
