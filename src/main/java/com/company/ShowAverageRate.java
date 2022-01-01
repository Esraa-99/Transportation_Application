package com.company;

import java.sql.*;
import java.util.*;

import static java.sql.DriverManager.getConnection;

public class ShowAverageRate implements Show_Rating{
    public ShowAverageRate()
    {

    }
    public void Show( String driver_id1)  {

        String url="jdbc:mysql://localhost:3306/transportation";
        String username = "root";
        String password = "";
        //String driver_id1="1";
        List<String> list = new ArrayList<>();
        try (Connection connection = getConnection(url, username, password)) {
            System.out.println("connected to the database ");
            PreparedStatement stmt =connection.prepareStatement("select rate from trip where driver_id ='"+driver_id1+"';");
            ResultSet result=stmt.executeQuery();
            while (result.next())
            {
                //System.out.println("rate: "+result.getString(1));
                list.add(result.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Opps,error!");
            e.printStackTrace();
        }
        float average=0 ;
        for(int i=0;i<list.size();i++)
        {
            average =average+ Integer.parseInt(list.get(i));
        }
        System.out.println("average of driver  = "+average/list.size());
    }
}
