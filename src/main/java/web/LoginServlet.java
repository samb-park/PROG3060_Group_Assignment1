package web;

import dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    //Creates an instance of DBUtil
    private DBUtil dbUtil = new DBUtil();

    //Handles the HTTP POST method.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Gets user information from the login.jsp.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            //Check if there is the user information in the db
            if(dbUtil.login(username, password))
            {
                //if success, redirect to success.jsp with parameter
                response.sendRedirect("index.jsp");
            }
            else
            {
                //if fails, redirect to login.jsp
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
