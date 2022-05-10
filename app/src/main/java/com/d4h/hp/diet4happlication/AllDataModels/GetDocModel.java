package com.d4h.hp.diet4happlication.AllDataModels;

public class GetDocModel {
    public GetDocModel(String filelink, String filename, String doc_id, String date) {
        this.filelink = filelink;
        this.filename = filename;
        this.doc_id = doc_id;
        this.date = date;
    }
     public GetDocModel(){

    }

    String filelink;
    String filename;
    String doc_id;
    String date;



    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
