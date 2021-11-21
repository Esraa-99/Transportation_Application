package com.company;

public class Trip {
    private float RatingOfTrip;
    private String Source;
    private String Destination;
    private int Trip_ID;
    private int user_id;
    private int driver_id;

    //setters
    public void setRatingOfTrip(float rateoftrip){
        this.RatingOfTrip = rateoftrip;
    }
    public void setSource(String source){
        this.Source = source;
    }
    public void setDestination(String destination){
        this.Destination = destination;
    }
    public void setTrip_ID(int trip_id){
        this.Trip_ID = trip_id;
    }
    public void setUser_id(int user_id) {this.user_id = user_id;}
    public void setDriver_id(int driver_id) {this.driver_id = driver_id;}

    //getters
    public float getRatingOfTrip(){
        return this.RatingOfTrip ;
    }
    public String getSource(){
        return this.Source;
    }
    public String getDestination(){
        return this.Destination ;
    }
    public int getTrip_ID(){
        return this.Trip_ID;
    }
    public int getUser_id() {return user_id;}
    public int getDriver_id() {return driver_id;}
    //Methods
    public void Start(){ }
    public void End(){ }

}


