<%@ page import="bean.GeographicArea" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="list-group mb-3">
                <a href="GeographicServlet?level=0" class="list-group-item list-group-item-action active">
                    1. Geographic Area Classification List
                </a>
                <a href="GeographicDetailServlet?name=Canada" class="list-group-item list-group-item-action">2. Geographic Area
                    Details</a>
                <a href="AgeListServlet?year=2021" class="list-group-item list-group-item-action">3. Age List</a>
            </div>
            <div class="btn-group mb-3 w-100" role="group">
                <a href="GeographicServlet?level=0" class="btn btn-outline-primary">LEVEL 0</a>
                <a href="GeographicServlet?level=1" class="btn btn-outline-primary">LEVEL 1</a>
                <a href="GeographicServlet?level=2" class="btn btn-outline-primary">LEVEL 2</a>
                <a href="GeographicServlet?level=3" class="btn btn-outline-primary">LEVEL 3</a>
            </div>
            <% List<GeographicArea> areas = (List<GeographicArea>) request.getAttribute("areas"); %>
            <% if (areas != null && !areas.isEmpty()) { %>
            <ul class="list-group">
                <% for (GeographicArea area : areas) { %>
                <li class="list-group-item">
                    <%= area.getName() %> (Level: <%= area.getLevel() %>)
                </li>
                <% } %>
            </ul>
            <% } else { %>
            <p>NO AREAS</p>
            <% } %>
        </div>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>