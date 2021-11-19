package com.company;

public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;


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
    return 0;
    }

     void Put_FaviorateSource_Areas(){

    }

    @Override
    public void Show() {

    }
}

