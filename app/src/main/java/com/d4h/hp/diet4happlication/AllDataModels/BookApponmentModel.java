package com.d4h.hp.diet4happlication.AllDataModels;

public class BookApponmentModel {

    private String appOnline,appOffline,onlineVideo,onlineAudio,appTiming,appDate,appComment;

    public BookApponmentModel() {
    }

    public BookApponmentModel(String appOnline, String appOffline, String onlineVideo, String onlineAudio, String appTiming, String appDate, String appComment) {
        this.appOnline = appOnline;
        this.appOffline = appOffline;
        this.onlineVideo = onlineVideo;
        this.onlineAudio = onlineAudio;
        this.appTiming = appTiming;
        this.appDate = appDate;
        this.appComment = appComment;
    }

    public String getAppOnline() {
        return appOnline;
    }

    public void setAppOnline(String appOnline) {
        this.appOnline = appOnline;
    }

    public String getAppOffline() {
        return appOffline;
    }

    public void setAppOffline(String appOffline) {
        this.appOffline = appOffline;
    }

    public String getOnlineVideo() {
        return onlineVideo;
    }

    public void setOnlineVideo(String onlineVideo) {
        this.onlineVideo = onlineVideo;
    }

    public String getOnlineAudio() {
        return onlineAudio;
    }

    public void setOnlineAudio(String onlineAudio) {
        this.onlineAudio = onlineAudio;
    }

    public String getAppTiming() {
        return appTiming;
    }

    public void setAppTiming(String appTiming) {
        this.appTiming = appTiming;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppComment() {
        return appComment;
    }

    public void setAppComment(String appComment) {
        this.appComment = appComment;
    }
}
