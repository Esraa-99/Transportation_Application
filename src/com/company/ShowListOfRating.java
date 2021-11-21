package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowListOfRating implements Show_Rating{
    public void Show(){
        String url="jdbc:mysql://localhost:3306/transportation";
        String username = "root";
        String password = "";
        String driver_id1="1";
        List<String> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to the database ");
            PreparedStatement stmt =connection.prepareStatement("select rate from trip where driver_id ='"+driver_id1+"';");
            ResultSet result=stmt.executeQuery();
            while (result.next())
            {
                System.out.println("rate: "+result.getString(1));
                list.add(result.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Opps,error!");
            e.printStackTrace();
        }
    }
}
