<%@ page import="bean.AgeList" %>
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
                <a href="GeographicServlet?level=0" class="list-group-item list-group-item-action ">
                    1. Geographic Area Classification List
                </a>
                <a href="GeographicDetailServlet?name=Canada" class="list-group-item list-group-item-action">2.
                    Geographic Area
                    Details</a>
                <a href="AgeListServlet?year=2021" class="list-group-item list-group-item-action active">3. Age List</a>
            </div>
            <select class="form-select" onchange="window.location.href=this.value">
                <option value="AgeListServlet?year=2021" ${param.get('year') == 2021 ? "selected" : ""}>
                    2021
                </option>
                <option value="AgeListServlet?year=2016" ${param.get('year') == 2016 ? "selected" : ""}>
                    2016
                </option>
            </select>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Age Group</th>
                    <th scope="col">Male</th>
                    <th scope="col">Female</th>
                </tr>
                </thead>

                <tbody>
                <% List<AgeList> a = (List<AgeList>) request.getAttribute("ageLists"); %>
                <% if (a != null && !a.isEmpty()) { %>
                <% for (AgeList area : a) { %>
                <tr>
                    <td>
                        <%= area.getAgeGroup() %>
                    </td>
                    <td>
                        <%= area.getMaleSum() %>
                    </td>
                    <td>
                        <%= area.getFemaleSum() %>
                    </td>
                </tr>
                <% } %>
                <% } %>
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