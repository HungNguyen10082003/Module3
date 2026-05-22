<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 24px; }
        .result { margin-top: 24px; font-size: 20px; }
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Result Division :1</h1>

    <c:if test="${not empty error}">
        <div class="error">Error: ${error}</div>
    </c:if>

    <c:if test="${empty error}">
        <div class="result">
            <strong>Operation:</strong> ${a} ${op} ${b} = <strong>${result}</strong>
        </div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/">Back</a></p>
</body>
</html>
