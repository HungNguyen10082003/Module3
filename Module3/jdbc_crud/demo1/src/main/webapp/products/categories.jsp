<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category và sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f6f8fb; }
        .container { max-width: 1100px; margin: 0 auto; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .btn-back { background: #374151; color: #fff; text-decoration: none; padding: 9px 14px; border-radius: 5px; }
        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 14px; }
        .card { background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 14px; }
        .card h3 { margin: 0 0 10px; color: #1f2937; }
        .product-item { border-top: 1px dashed #d1d5db; padding: 8px 0; }
        .product-item:first-child { border-top: none; }
        .name { font-weight: 600; }
        .meta { color: #4b5563; font-size: 14px; }
        .empty { color: #9ca3af; font-style: italic; }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Danh sách category và sản phẩm</h1>
        <a class="btn-back" href="${pageContext.request.contextPath}/product?action=list">Quay lại danh sách sản phẩm</a>
    </div>

    <div class="grid">
        <c:forEach var="group" items="${categoryGroups}">
            <div class="card">
                <h3>${group.category.name}</h3>
                <c:choose>
                    <c:when test="${empty group.products}">
                        <p class="empty">Chưa có sản phẩm trong category này.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="product" items="${group.products}">
                            <div class="product-item">
                                <div class="name">${product.productName}</div>
                                <div class="meta">Giá: ${product.price} USD | Số lượng: ${product.quantity}</div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
