package com.company;
public abstract class Person {

    private String User_Name;
    private String Mobile_Number;
    private String Email;
    private String Password;

    //setters
    private void  setUser_name(String username){
        this.User_Name =username;
    }
    private void  setMobile_number(String mobile_number){
        this.Mobile_Number =mobile_number;
    }
    private void  setEmail(String email){
        this.Email =email;
    }
    private void  setPassword(String password){
        this.Password =password;
    }
    //getters
    private String getUser_name(){
        return User_Name;
    }
    private String getMobile_number(){
        return this.Mobile_Number;
    }
    private String getEmail(){
        return this.Email;
    }
    private String getPassword(){
        return this.Password;
    }
    //Methods
    public void Login(){

    }
}
