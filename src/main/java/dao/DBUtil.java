package dao;

import bean.AgeList;

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

    //Get all GeographicalAreas
    public List<GeographicArea> getGeographicalAreas() {
        List<GeographicArea> areas = new ArrayList<>();

        //Query to get the geographic areas by level
        String query = "SELECT geographicAreaID, code, level, name, alternativeCode FROM geographicarea";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
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

    public GeographicArea getGeographicalAreaByName(String name) {
        GeographicArea area = new GeographicArea();

        String query = "SELECT geographicAreaID, code, level, name, alternativeCode FROM geographicarea WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            //Executes the query and stores the result in the areas list
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    area.setGeographicAreaID(rs.getInt("geographicAreaID"));
                    area.setCode(rs.getInt("code"));
                    area.setLevel(rs.getInt("level"));
                    area.setName(rs.getString("name"));

                    return area;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return area;
    }

    public int getTotalPopulationByArea(int areaId) {
        int total = 0;

        String query = "SELECT name, SUM(combined) as c_s FROM geographicarea LEFT JOIN canadacensusdb.age a on geographicarea.geographicAreaID = a.geographicArea WHERE a.censusYear = 1 and geographicarea.geographicAreaID = ? GROUP BY name;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, areaId);
            //Executes the query and stores the result in the areas list
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    total = rs.getInt("c_s");

                    return total;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<AgeList> getAgeListByYearCode(int yearCode) {
        String query = "SELECT description, SUM(a.male) as m_s, SUM(a.female) as fm_s, a.censusYear from agegroup " +
                "LEFT JOIN age a on agegroup.ageGroupID = a.ageGroup " +
                "WHERE a.censusYear = ? " +
                "GROUP BY description, a.censusYear;";

        List<AgeList> ageList = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, yearCode);
            //Executes the query and stores the result in the areas list
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    AgeList ageList1 = new AgeList();

                    ageList1.setAgeGroup(rs.getString("description"));
                    ageList1.setMaleSum(rs.getString("m_s"));
                    ageList1.setFemaleSum(rs.getString("fm_s"));
                    ageList.add(ageList1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ageList;
    }

}
