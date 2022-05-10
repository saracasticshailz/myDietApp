package com.d4h.hp.diet4happlication.AllDataModels;

public class FooddieryModel {

    String date,mealType,mealName,mealTime,mealQuantity;



    public FooddieryModel(String date, String mealType, String mealName, String mealTime, String mealQuantity) {
        this.date = date;
        this.mealType = mealType;
        this.mealName = mealName;
        this.mealTime = mealTime;
        this.mealQuantity = mealQuantity;
    }

    public FooddieryModel() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }

    public void setMealQuantity(String mealQuantity) {
        this.mealQuantity = mealQuantity;
    }
}
