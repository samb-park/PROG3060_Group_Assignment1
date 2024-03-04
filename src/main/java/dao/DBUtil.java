package dao;

import bean.User;

import java.sql.*;

public class DBUtil {

    // Indicates the URL for the CanadaCenSub database.
    private static final String CanadaCenSubUrl = "jdbc:mysql://localhost:3306/canadacensusdb";
    // Indicates the username for the database.
    private static String Username = null;
    // Indicates the password for the database.
    private static String Password = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CanadaCenSubUrl, Username, Password);
        return conn;
    }


    //Checks if there is registered user in the db
    public boolean login(String username, String password){

        try {
            //Stores the username and password in the memory
            Username = username;
            Password = password;

            //Tries to connect to the database
            Connection conn = getConnection();

            //Close the connection
            conn.close();
            return  true;
        }
        catch (Exception e)
        {
            //If the connection fails, it returns false and clears the username and password
            Username = null;
            Password = null;
            return false;
        }
    }
}
