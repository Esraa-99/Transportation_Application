package com.company;
public abstract class Person {

    private String User_Name;
    private String Mobile_Number;
    private String Email;
    private String Password;

    //setters
    public void  setUser_name(String username){
        this.User_Name =username;
    }
    public void  setMobile_number(String mobile_number){
        this.Mobile_Number =mobile_number;
    }
    public void  setEmail(String email){
        this.Email =email;
    }
    public void  setPassword(String password){
        this.Password =password;
    }
    //getters
    public String getUser_name(){
        return User_Name;
    }
    public String getMobile_number(){
        return this.Mobile_Number;
    }
    public String getEmail(){
        return this.Email;
    }
    public String getPassword(){
        return this.Password;
    }
    //Methods
    public void Login(){

    }
}
