package com.d4h.hp.diet4happlication.AllDataModels;

public class ReviewModel {
    String startDt;
    String endDt;
    String score;
    String viewrep;
    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getViewrep() {
        return viewrep;
    }

    public void setViewrep(String viewrep) {
        this.viewrep = viewrep;
    }
    public  ReviewModel(String startDt,String endDt,String score,String viewrep){
        this.startDt=startDt;
        this.endDt=endDt;
        this.score=score;
        this.viewrep=viewrep;

    }
    public ReviewModel(){

    }


}