package web;

import bean.AgeList;
import bean.GeographicArea;
import dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// Servlet to handle for graphic detail with total
@WebServlet(name = "AgeListServlet", urlPatterns = {"/AgeListServlet"})
public class AgeListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get name from parameter
        String year = request.getParameter("year");
        int yearCode = 0;

        if (year == null) {
            year = "2021";
        }

        if (year.equals("2021")) {
            yearCode = 1;
        } else if (year.equals("2016")) {
            yearCode = 2;
        }

        DBUtil dbUtil = new DBUtil();

        List<AgeList> ageList = dbUtil.getAgeListByYearCode(yearCode);

        request.setAttribute("ageLists", ageList);

        // give jsp with the specific geographicalarea
        request.getRequestDispatcher("ageList.jsp").forward(request, response);
    }
}
