<%@ page import="bean.GeographicArea" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="list-group mb-3">
                <a href="GeographicServlet?level=0" class="list-group-item list-group-item-action">
                    1. Geographic Area Classification List
                </a>
                <a href="GeographicDetailServlet?name=Canada" class="list-group-item list-group-item-action active">2.
                    Geographic Area
                    Details</a>
                <a href="AgeListServlet?year=2021" class="list-group-item list-group-item-action">3. Age List</a>
            </div>

            <div>
                Geographic Area Details
            </div>

            <select class="form-select" onchange="window.location.href=this.value">
                <option selected>
                    Please select Geographic Area
                </option>

                <% List<GeographicArea> areas = (List<GeographicArea>) request.getAttribute("areas"); %>
                <% if (areas != null && !areas.isEmpty()) { %>
                <% for (GeographicArea area : areas) { %>
                <option value="<%= "GeographicDetailServlet?name=" + area.getName() %>">
                    <%= area.getName() %>
                </option>
                <% } %>
                <% } else { %>
                <option>NO AREAS</option>
                <% } %>
            </select>

            <jsp:useBean id="area" class="bean.GeographicArea" scope="request" />
            <table class="table">
                <tbody>
                <tr>
                    <th scope="row">Name</th>
                    <td><jsp:getProperty name="area" property="name" /></td>
                </tr>
                <tr>
                    <th scope="row">Code</th>
                    <td><jsp:getProperty name="area" property="code" /></td>
                </tr>
                <tr>
                    <th scope="row">Level</th>
                    <td><jsp:getProperty name="area" property="level" /></td>
                </tr>
                <tr>
                    <th scope="row">Total Population</th>
                    <td><jsp:getProperty name="area" property="total"/></td>
                </tr>
                </tbody>
            </table>
        </div>


    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>