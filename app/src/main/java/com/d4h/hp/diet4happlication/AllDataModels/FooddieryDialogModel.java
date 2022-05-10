package com.d4h.hp.diet4happlication.AllDataModels;

public class FooddieryDialogModel {

    int mealQuantity,getMealQuantity;
    String mealDate,mealTime,diet;

    public FooddieryDialogModel(int mealQuantity, int getMealQuantity, String mealDate, String mealTime, String diet) {
        this.mealQuantity = mealQuantity;
        this.getMealQuantity = getMealQuantity;
        this.mealDate = mealDate;
        this.mealTime = mealTime;
        this.diet = diet;
    }

    public FooddieryDialogModel() {
    }

    public int getMealQuantity() {
        return mealQuantity;
    }

    public void setMealQuantity(int mealQuantity) {
        this.mealQuantity = mealQuantity;
    }

    public int getGetMealQuantity() {
        return getMealQuantity;
    }

    public void setGetMealQuantity(int getMealQuantity) {
        this.getMealQuantity = getMealQuantity;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}
