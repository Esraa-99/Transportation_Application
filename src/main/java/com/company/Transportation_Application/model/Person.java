package com.company.Transportation_Application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.Scanner;

@Repository
public class Person {
    private  String User_Name;
    private String Mobile_Number;
    public String Email;
    private String Password;
    @RequestMapping("model/Person")
    //setters
    public void  setUser_name(String username){
        this.User_Name =username;
    }
    public void  setMobile_number(String mobile_number){
        this.Mobile_Number =mobile_number;
    }
    @PostMapping
    public void  setEmail(@JsonProperty String email){
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
    //login
    @RequestMapping("model/Person/Login")
    @PostMapping
    public int Login(@JsonProperty String Email,@JsonProperty String Pass,@JsonProperty int choice) {
        int var = check_person(Email, Pass,choice);
        return var;
    }
    @GetMapping
    public int check_person(@JsonProperty String Email,@JsonProperty String Pass,@JsonProperty int choice)
    {
        Scanner sc = new Scanner(System.in);
        //user
        switch(choice) {
            case 1: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from users where Email= '" + Email + "' and password= '" + Pass + "'and status='active';");
                    if (RS.next()) {
                        setEmail(Email);
                        this.Email=Email;

                    } else {
                        return  0;
                    }
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return 1;

            }
            //driver
            case 2: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from drivers where Email= '" + Email + "' and Password= '" + Pass + "' and status='active';");
                    if (!RS.next())
                        return  0;
                    RS.close();
                    stmt.close();
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return 2;

            }
            case 3:
            {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from admin where Email= '" + Email + "' and password= '" + Pass + "';");
                    if (!RS.next())
                        return  0;
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
                return 3;
            }
            default: {
                return 4;
            }
        }
    }
}
