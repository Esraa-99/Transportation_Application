package com.company;
public class User extends Person implements Registration,Show_Rating {
    private String Source;
    private String Destination;

    //setters
    private void setSource(String source){
        this.Source = source;
    }
    private void setDestination(String destination){
        this.Destination = destination;
    }
    //getters
    private String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
//Methods
@Override
public void Register() {

}
    void Requset(){

    }
    void Rate_Driver(){ }
    @Override
    public void Show() {

    }
}
