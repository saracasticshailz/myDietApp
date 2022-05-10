package com.d4h.hp.diet4happlication.AllDataModels;

public class NotificationModel {

    private String notiHedaer,notiContent;

    public String getNotiTime() {
        return notiTime;
    }

    public void setNotiTime(String notiTime) {
        this.notiTime = notiTime;
    }

    private String notiTime;

    public NotificationModel(String notiHedaer, String notiContent,String notiTime ) {
        this.notiHedaer = notiHedaer;
        this.notiContent = notiContent;
        this.notiTime=notiTime;
    }
    public NotificationModel() {
    }
    public String getNotiHedaer() {
        return notiHedaer;
    }

    public void setNotiHedaer(String notiHedaer) {
        this.notiHedaer = notiHedaer;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }
}