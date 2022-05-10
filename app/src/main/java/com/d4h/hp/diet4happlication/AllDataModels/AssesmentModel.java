package com.d4h.hp.diet4happlication.AllDataModels;

public class AssesmentModel {

    String assDate,assWeight,assBodyFats,assBosyAge,assMetabolism,
            assVisceral,assBmi,assWaist,assHips,assRemark;

    public AssesmentModel(String assDate, String assWeight, String assBodyFats, String assBosyAge,
                          String assMetabolism, String assVisceral,
                          String assBmi, String assWaist, String assHips, String assRemark) {
        this.assDate = assDate;
        this.assWeight = assWeight;
        this.assBodyFats = assBodyFats;
        this.assBosyAge = assBosyAge;
        this.assMetabolism = assMetabolism;
        this.assVisceral = assVisceral;
        this.assBmi = assBmi;
        this.assWaist = assWaist;
        this.assHips = assHips;
        this.assRemark = assRemark;
    }

    public AssesmentModel() {
    }

    public String getAssDate() {
        return assDate;
    }

    public void setAssDate(String assDate) {
        this.assDate = assDate;
    }

    public String getAssWeight() {
        return assWeight;
    }

    public void setAssWeight(String assWeight) {
        this.assWeight = assWeight;
    }

    public String getAssBodyFats() {
        return assBodyFats;
    }

    public void setAssBodyFats(String assBodyFats) {
        this.assBodyFats = assBodyFats;
    }

    public String getAssBosyAge() {
        return assBosyAge;
    }

    public void setAssBosyAge(String assBosyAge) {
        this.assBosyAge = assBosyAge;
    }

    public String getAssMetabolism() {
        return assMetabolism;
    }

    public void setAssMetabolism(String assMetabolism) {
        this.assMetabolism = assMetabolism;
    }

    public String getAssVisceral() {
        return assVisceral;
    }

    public void setAssVisceral(String assVisceral) {
        this.assVisceral = assVisceral;
    }

    public String getAssBmi() {
        return assBmi;
    }

    public void setAssBmi(String assBmi) {
        this.assBmi = assBmi;
    }

    public String getAssWaist() {
        return assWaist;
    }

    public void setAssWaist(String assWaist) {
        this.assWaist = assWaist;
    }

    public String getAssHips() {
        return assHips;
    }

    public void setAssHips(String assHips) {
        this.assHips = assHips;
    }

    public String getAssRemark() {
        return assRemark;
    }

    public void setAssRemark(String assRemark) {
        this.assRemark = assRemark;
    }
}
