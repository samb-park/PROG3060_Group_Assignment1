package web;

import bean.GeographicArea;
import dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GeographicServlet", urlPatterns = {"/GeographicServlet"})
public class GeographicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String levelStr = request.getParameter("level");
        int level = 0;

        //Parse the level parameter to integer
        if(levelStr != null){
            try {
                level = Integer.parseInt(levelStr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //Get the list of geographic areas by level
        DBUtil dbUtil = new DBUtil();
        List<GeographicArea> areas = dbUtil.getGeographicalAreasByLevel(level);

        request.setAttribute("areas", areas);
        request.getRequestDispatcher("geographicList.jsp").forward(request, response);
    }
}
