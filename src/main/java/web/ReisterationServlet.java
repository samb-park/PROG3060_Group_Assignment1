package web;

import bean.User;
import common.constant;
import dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", value = "/signup")
public class ReisterationServlet extends HttpServlet {
    private DBUtil dbUtil = new DBUtil();

    //Overrides to handles the HTTP POST method.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        //Gets parameters from the Registration.jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setPassword(password);

        try {
            //Registers a user
            int result =  dbUtil.Register(user);

            if(result == constant.RegisterSuccess){
                response.sendRedirect("login.jsp");
            }
            else{
                response.sendRedirect("signup.jsp");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
