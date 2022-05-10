package com.d4h.hp.diet4happlication.AllDataModels;

public class InvoiceModel {
    String invName,invPataintName,invPlanName,invDuration,invpaymentDate,invProgStartDate,invSubOver,invPlanCost,invOfferCost,
    invAddDis,invNetPay,invAmtPaid,invBalance;

    public InvoiceModel(String invName, String invPataintName, String invPlanName, String invDuration, String
            invpaymentDate, String invProgStartDate, String invSubOver, String invPlanCost, String invOfferCost,
                        String invAddDis, String invNetPay, String invAmtPaid, String invBalance) {

        this.invName = invName;
        this.invPataintName = invPataintName;
        this.invPlanName = invPlanName;
        this.invDuration = invDuration;
        this.invpaymentDate = invpaymentDate;
        this.invProgStartDate = invProgStartDate;
        this.invSubOver = invSubOver;
        this.invPlanCost = invPlanCost;
        this.invOfferCost = invOfferCost;
        this.invAddDis = invAddDis;
        this.invNetPay = invNetPay;
        this.invAmtPaid = invAmtPaid;
        this.invBalance = invBalance;
    }

    public InvoiceModel() {
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getInvPataintName() {
        return invPataintName;
    }

    public void setInvPataintName(String invPataintName) {
        this.invPataintName = invPataintName;
    }

    public String getInvPlanName() {
        return invPlanName;
    }

    public void setInvPlanName(String invPlanName) {
        this.invPlanName = invPlanName;
    }

    public String getInvDuration() {
        return invDuration;
    }

    public void setInvDuration(String invDuration) {
        this.invDuration = invDuration;
    }

    public String getInvpaymentDate() {
        return invpaymentDate;
    }

    public void setInvpaymentDate(String invpaymentDate) {
        this.invpaymentDate = invpaymentDate;
    }

    public String getInvProgStartDate() {
        return invProgStartDate;
    }

    public void setInvProgStartDate(String invProgStartDate) {
        this.invProgStartDate = invProgStartDate;
    }

    public String getInvSubOver() {
        return invSubOver;
    }

    public void setInvSubOver(String invSubOver) {
        this.invSubOver = invSubOver;
    }

    public String getInvPlanCost() {
        return invPlanCost;
    }

    public void setInvPlanCost(String invPlanCost) {
        this.invPlanCost = invPlanCost;
    }

    public String getInvOfferCost() {
        return invOfferCost;
    }

    public void setInvOfferCost(String invOfferCost) {
        this.invOfferCost = invOfferCost;
    }

    public String getInvAddDis() {
        return invAddDis;
    }

    public void setInvAddDis(String invAddDis) {
        this.invAddDis = invAddDis;
    }

    public String getInvNetPay() {
        return invNetPay;
    }

    public void setInvNetPay(String invNetPay) {
        this.invNetPay = invNetPay;
    }

    public String getInvAmtPaid() {
        return invAmtPaid;
    }

    public void setInvAmtPaid(String invAmtPaid) {
        this.invAmtPaid = invAmtPaid;
    }

    public String getInvBalance() {
        return invBalance;
    }

    public void setInvBalance(String invBalance) {
        this.invBalance = invBalance;
    }
}
