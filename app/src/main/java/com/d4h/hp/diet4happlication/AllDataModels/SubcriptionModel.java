package com.d4h.hp.diet4happlication.AllDataModels;

public class SubcriptionModel {

    String subPlanName;
    String subDuration;
    String subOver;
    String subPaidAmt;

    public String getSubPayId() {
        return subPayId;
    }

    public void setSubPayId(String subPayId) {
        this.subPayId = subPayId;
    }

    String subPayId;

    public SubcriptionModel(String subPlanName, String subDuration, String subOver, String subPaidAmt) {
        this.subPlanName = subPlanName;
        this.subDuration = subDuration;
        this.subOver = subOver;
        this.subPaidAmt = subPaidAmt;
    }

    public SubcriptionModel() {
    }

    public String getSubPlanName() {
        return subPlanName;
    }

    public void setSubPlanName(String subPlanName) {
        this.subPlanName = subPlanName;
    }

    public String getSubDuration() {
        return subDuration;
    }

    public void setSubDuration(String subDuration) {
        this.subDuration = subDuration;
    }

    public String getSubOver() {
        return subOver;
    }

    public void setSubOver(String subOver) {
        this.subOver = subOver;
    }

    public String getSubPaidAmt() {
        return subPaidAmt;
    }

    public void setSubPaidAmt(String subPaidAmt) {
        this.subPaidAmt = subPaidAmt;
    }
}
