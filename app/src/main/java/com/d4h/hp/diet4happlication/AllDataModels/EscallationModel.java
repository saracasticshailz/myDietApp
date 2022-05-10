package com.d4h.hp.diet4happlication.AllDataModels;

public class EscallationModel {
    String escDate;
    String escStatus;
    String escSubject;
    String escView;
    String escId;

    public String getEscId() {
        return escId;
    }

    public void setEscId(String escId) {
        this.escId = escId;
    }

    public EscallationModel(String escDate, String escStatus, String escSubject, String escView) {
        this.escDate = escDate;
        this.escStatus = escStatus;
        this.escSubject = escSubject;
        this.escView = escView;
    }

    public EscallationModel() {
    }

    public String getEscDate() {
        return escDate;
    }

    public void setEscDate(String escDate) {
        this.escDate = escDate;
    }

    public String getEscStatus() {
        return escStatus;
    }

    public void setEscStatus(String escStatus) {
        this.escStatus = escStatus;
    }

    public String getEscSubject() {
        return escSubject;
    }

    public void setEscSubject(String escSubject) {
        this.escSubject = escSubject;
    }

    public String getEscView() {
        return escView;
    }

    public void setEscView(String escView) {
        this.escView = escView;
    }
}
