<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin-right: 5px; text-decoration: none; }
        .btn-add { background-color: #4CAF50; color: white; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-view { background-color: #008CBA; color: white; }
        .search-box { margin-bottom: 20px; }
    </style>
</head>
<body>
    <h1>Quản lý sản phẩm</h1>
    
    <div class="search-box">
        <form method="get" action="${pageContext.request.contextPath}/product">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" placeholder="Tìm kiếm sản phẩm..." value="${keyword}">
            <button type="submit">Tìm kiếm</button>
        </form>
    </div>

    <a href="${pageContext.request.contextPath}/products/add.jsp" class="btn btn-add">➕ Thêm sản phẩm mới</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Mô tả</th>
                <th>Số lượng</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.productName}</td>
                    <td>${product.price} VND</td>
                    <td>${product.description}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product?action=view&id=${product.id}" class="btn btn-view">👁️ Xem</a>
                        <a href="${pageContext.request.contextPath}/product?action=edit&id=${product.id}" class="btn btn-edit">✏️ Sửa</a>
                        <a href="${pageContext.request.contextPath}/product?action=delete&id=${product.id}" class="btn btn-delete" onclick="return confirm('Bạn chắc chứ?')">🗑️ Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty products}">
        <p style="text-align: center; color: red;">Không có sản phẩm nào!</p>
    </c:if>
</body>
</html>
