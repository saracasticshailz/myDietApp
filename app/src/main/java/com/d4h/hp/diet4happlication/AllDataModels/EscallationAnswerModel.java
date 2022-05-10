package com.d4h.hp.diet4happlication.AllDataModels;

public class EscallationAnswerModel {


    private String strSubject,strIssue,strAnswer;

    public EscallationAnswerModel() {
    }

    public EscallationAnswerModel(String strSubject, String strIssue, String strAnswer) {
        this.strSubject = strSubject;
        this.strIssue = strIssue;
        this.strAnswer = strAnswer;
    }

    public String getStrSubject() {
        return strSubject;
    }

    public void setStrSubject(String strSubject) {
        this.strSubject = strSubject;
    }

    public String getStrIssue() {
        return strIssue;
    }

    public void setStrIssue(String strIssue) {
        this.strIssue = strIssue;
    }

    public String getStrAnswer() {
        return strAnswer;
    }

    public void setStrAnswer(String strAnswer) {
        this.strAnswer = strAnswer;
    }
}
