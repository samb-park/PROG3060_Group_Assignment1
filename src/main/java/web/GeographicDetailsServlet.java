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

// Servlet to handle for graphic detail with total
@WebServlet(name = "GeographicDetailServlet", urlPatterns = {"/GeographicDetailServlet"})
public class GeographicDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get name from parameter
        String areaName = request.getParameter("name");

        if (areaName == null) {
            areaName = "Canada";
        }

        DBUtil dbUtil = new DBUtil();

        // push data on the area gegraphicalarea
        GeographicArea area = dbUtil.getGeographicalAreaByName(areaName);
        List<GeographicArea> areas = dbUtil.getGeographicalAreas();
        area.setTotal(dbUtil.getTotalPopulationByArea(area.getGeographicAreaID()));

        request.setAttribute("areas", areas);
        request.setAttribute("area", area);

        // give jsp with the specific geographicalarea
        request.getRequestDispatcher("geographicDetail.jsp").forward(request, response);
    }
}
