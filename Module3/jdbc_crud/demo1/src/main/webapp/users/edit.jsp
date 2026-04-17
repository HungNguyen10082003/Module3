<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa người dùng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        .container { margin: 0 auto; }
        form { border: 1px solid #ddd; padding: 20px; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input, textarea, select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        textarea { resize: vertical; min-height: 80px; }
        button { background-color: #2196F3; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px; }
        button:hover { background-color: #0b7dda; }
        a { color: #2196F3; text-decoration: none; }
        .error { color: red; margin: 10px 0; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh sửa người dùng</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/user">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${user.id}">
            
            <div class="form-group">
                <label for="fullName">Họ tên:</label>
                <input type="text" id="fullName" name="fullName" value="${user.fullName}" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" required>
            </div>

            <div class="form-group">
                <label for="phone">Điện thoại:</label>
                <input type="text" id="phone" name="phone" value="${user.phone}">
            </div>

            <div class="form-group">
                <label for="country">Quốc gia:</label>
                <select id="country" name="country" required>
                    <option value="">-- Chọn quốc gia --</option>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country}" <c:if test="${country == user.country}">selected</c:if>>${country}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <textarea id="address" name="address">${user.address}</textarea>
            </div>

            <button type="submit">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/user?action=list">← Quay lại</a>
        </form>
    </div>
</body>
</html>
