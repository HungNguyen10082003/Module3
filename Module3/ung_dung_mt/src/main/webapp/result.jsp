<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>
<body>
<h1>Result:</h1>

<%
    Object error = request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
} else {
%>
<p>
    <%= request.getAttribute("firstOperand") %>
    <%
        String operator = (String) request.getAttribute("operator");
        String symbol = "";
        switch (operator) {
            case "Addition": symbol = "+"; break;
            case "Subtraction": symbol = "-"; break;
            case "Multiplication": symbol = "*"; break;
            case "Division": symbol = "/"; break;
        }
    %>
    <%= symbol %>
    <%= request.getAttribute("secondOperand") %>
    =
    <%= request.getAttribute("result") %>
</p>
<%
    }
%>

</body>
</html>