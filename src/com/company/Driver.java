package com.company;
import java.lang.Math;
public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;
    private String[] FaviorateSource_Areas={};

    //setters
    private void  setNational_ID(String national_id){
        this.National_ID =national_id;
    }
    private void  setDriving_license(String drivinglicense){
        this.Driving_license =drivinglicense;
    }

    //getters
    private String setNational_ID(){
        return this.National_ID ;
    }
    private String setDriving_license(){
        return this.Driving_license ;
    }

    //Methods
    public void Register() {

    }
    int Offer(){
        int offer =(int)(Math.random()*(349)+50);
        return offer;
    }

     void Put_FaviorateSource_Areas(){

    }

    @Override
    public void Show() {

    }
}

