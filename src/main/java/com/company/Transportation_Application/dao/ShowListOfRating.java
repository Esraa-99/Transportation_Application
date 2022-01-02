package com.company.Transportation_Application.dao;

import com.company.Transportation_Application.model.Show_Rating;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowListOfRating implements Show_Rating {
    @Override
    public void Show(@JsonProperty String email2){
        String url="jdbc:mysql://localhost:3306/transportation";
        String username = "root";
        String password = "";
        // driver_id1="1";
        List<String> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String driver_id1="";
            // System.out.println("connected to the database ");
            PreparedStatement stmt1 =connection.prepareStatement("select National_ID from drivers where Email ='"+email2+"';");
            ResultSet result1=stmt1.executeQuery();
            if(result1.next())
            {
                driver_id1 =result1.getString(1);
            }

            System.out.println(email2+"      "+driver_id1);
            PreparedStatement stmt =connection.prepareStatement("select rate from trip where driver_id ='"+driver_id1+"';");
            ResultSet result=stmt.executeQuery();
            while (result.next())
            {
               // System.out.println("rate: "+result.getString(1));
                list.add(result.getString(1));
            }
        } catch (SQLException e) {

            System.out.println(e);
        }
    }
}
