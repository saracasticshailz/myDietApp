package com.d4h.hp.diet4happlication.Utilities;

import java.util.ArrayList;

public class DataHolder {

    public ArrayList<String> weightDate=new ArrayList<String>();
    public ArrayList<String> weightWeight=new ArrayList<String>();
    public ArrayList<String> BmiDate=new ArrayList<String>();
    public ArrayList<String> Bmicount=new ArrayList<String>();



    public static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
