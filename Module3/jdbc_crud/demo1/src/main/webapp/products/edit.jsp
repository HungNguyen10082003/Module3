<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        form { border: 1px solid #ddd; padding: 20px; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input, textarea { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        textarea { resize: vertical; min-height: 100px; }
        button { background-color: #2196F3; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px; }
        button:hover { background-color: #0b7dda; }
        a { color: #2196F3; text-decoration: none; }
        .error { color: red; margin: 10px 0; }
    </style>
</head>
<body>
    <h1>Chỉnh sửa sản phẩm</h1>
    
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/product">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${product.id}">
        
        <div class="form-group">
            <label for="productName">Tên sản phẩm:</label>
            <input type="text" id="productName" name="productName" value="${product.productName}" required>
        </div>

        <div class="form-group">
            <label for="price">Giá (VND):</label>
            <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>
        </div>

        <div class="form-group">
            <label for="description">Mô tả:</label>
            <textarea id="description" name="description">${product.description}</textarea>
        </div>

        <div class="form-group">
            <label for="quantity">Số lượng:</label>
            <input type="number" id="quantity" name="quantity" value="${product.quantity}" required>
        </div>

        <button type="submit">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/product?action=list">← Quay lại</a>
    </form>
</body>
</html>
