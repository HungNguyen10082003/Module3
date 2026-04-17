<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm người dùng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        .container { margin: 0 auto; }
        form { border: 1px solid #ddd; padding: 20px; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input, textarea, select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        textarea { resize: vertical; min-height: 80px; }
        button { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px; }
        button:hover { background-color: #45a049; }
        a { color: #2196F3; text-decoration: none; }
        .error { color: red; margin: 10px 0; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm người dùng mới</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/user">
            <input type="hidden" name="action" value="create">
            
            <div class="form-group">
                <label for="fullName">Họ tên:</label>
                <input type="text" id="fullName" name="fullName" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="phone">Điện thoại:</label>
                <input type="text" id="phone" name="phone">
            </div>

            <div class="form-group">
                <label for="country">Quốc gia:</label>
                <input type="text" id="country" name="country" required placeholder="Nhập tên quốc gia">
            </div>

            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <textarea id="address" name="address"></textarea>
            </div>

            <button type="submit">Thêm người dùng</button>
            <a href="${pageContext.request.contextPath}/user?action=list">← Quay lại</a>
        </form>
    </div>
</body>
</html>
