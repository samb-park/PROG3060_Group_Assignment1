package dao;

import bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.GeographicArea;

public class DBUtil {

    // Indicates the URL for the CanadaCenSub database.
    private static final String CanadaCenSubUrl = "jdbc:mysql://localhost:3306/canadacensusdb";
    // Indicates the connection to the database.
    private static Connection conn = null;

    public Connection getConnection(String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Stores the connection in the conn variable
        conn = DriverManager.getConnection(CanadaCenSubUrl, username, password);
        return conn;
    }

    //Checks if there is registered user in the db
    public boolean login(String username, String password){

        try {
            //Tries to connect to the database
            Connection conn = getConnection(username, password);

            if(conn != null)
                return  true;
            else
                return false;
        }
        catch (Exception e)
        {
            //If the connection fails, it returns false and clears the connection
            conn = null;
            return false;
        }
    }

    //Gets the list of geographic areas by level
    public List<GeographicArea> getGeographicalAreasByLevel(int level) {
        List<GeographicArea> areas = new ArrayList<>();

        //Query to get the geographic areas by level
        String query = "SELECT geographicAreaID, code, level, name, alternativeCode FROM geographicarea WHERE level = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, level);
            //Executes the query and stores the result in the areas list
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    GeographicArea area = new GeographicArea();
                    area.setGeographicAreaID(rs.getInt("geographicAreaID"));
                    area.setCode(rs.getInt("code"));
                    area.setLevel(rs.getInt("level"));
                    area.setName(rs.getString("name"));
                    area.setAlternativeCode(rs.getInt("alternativeCode"));
                    areas.add(area);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return areas;
    }

}
