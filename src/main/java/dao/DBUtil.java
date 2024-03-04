package dao;

import bean.User;

import java.sql.*;

public class DBUtil {

    // Indicates the URL for the HRManager database.
    private static final String HrMangerUrl = "jdbc:mysql://localhost:3306/hrmanager";
    // Indicates the URL for the CanadaCenSub database.
    private static final String CanadaCenSubUrl = "jdbc:mysql://localhost:3306/canadacensubdb";
    // Indicates the username for the database.
    private static final String Username = "root";
    // Indicates the password for the database.
    private static final String Password = "1234";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(HrMangerUrl, Username, Password);
        return conn;
    }

    //Stores user information into the db
    public int Register(User user) throws ClassNotFoundException, SQLException {

        String INSERT_USERS_SQL = "INSERT INTO employee" +
                " (id, username, password) VALUES " +
                " (?, ?, ?);";

        // Stores username and password in the database.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    //Checks if there is registered user in the db
    public boolean login(String username, String password) throws ClassNotFoundException, SQLException {
        String SELECT_QUERY = "SELECT * FROM employee WHERE userName = ? AND password = ?";

        // Checks username and password in the database.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next())
                    return true;
                else
                    return false;
            }
        }
    }


}
