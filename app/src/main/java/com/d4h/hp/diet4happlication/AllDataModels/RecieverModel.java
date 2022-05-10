package com.d4h.hp.diet4happlication.AllDataModels;

public class RecieverModel {
    public String getRecievermsg() {
        return Recievermsg;
    }

    public void setRecievermsg(String recievermsg) {
        Recievermsg = recievermsg;
    }

    public String getRecievertime() {
        return Recievertime;
    }

    public void setRecievertime(String recievertime) {
        Recievertime = recievertime;
    }


    String Recievermsg;
    String Recievertime;
    String send_message_time;
    String send_message_body;
    String recvd_message_date,send_message_date;

    public RecieverModel(String recievermsg, String recievertime, String send_message_time, String send_message_body, String recvd_message_date, String send_message_date) {
        Recievermsg = recievermsg;
        Recievertime = recievertime;
        this.send_message_time = send_message_time;
        this.send_message_body = send_message_body;
        this.recvd_message_date = recvd_message_date;
        this.send_message_date = send_message_date;
    }

    public String getRecvd_message_date() {
        return recvd_message_date;
    }

    public void setRecvd_message_date(String recvd_message_date) {
        this.recvd_message_date = recvd_message_date;
    }

    public String getSend_message_date() {
        return send_message_date;
    }

    public void setSend_message_date(String send_message_date) {
        this.send_message_date = send_message_date;
    }



    public String getSend_message_time() {
        return send_message_time;
    }

    public void setSend_message_time(String send_message_time) {
        this.send_message_time = send_message_time;
    }

    public String getSend_message_body() {
        return send_message_body;
    }

    public void setSend_message_body(String send_message_body) {
        this.send_message_body = send_message_body;
    }


    public RecieverModel(){}
}