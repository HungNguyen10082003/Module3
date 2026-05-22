<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Selected Condiments</title>
    <style> body { font-family: Arial, sans-serif; padding: 20px; } </style>
</head>
<body>
<h1>Selected Condiments</h1>

<%
    String[] selected = (String[]) request.getAttribute("selected");
    if (selected == null || selected.length == 0) {
%>
    <p>No condiments selected.</p>
<%
    } else {
%>
    <ul>
    <% for (String c : selected) { %>
        <li><%= c %></li>
    <% } %>
    </ul>
<%
    }
%>

<p><a href="${pageContext.request.contextPath}/condiments">Back</a></p>

</body>
</html>
