<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        .product-detail { border: 1px solid #ddd; padding: 20px; border-radius: 5px; }
        .detail-row { margin-bottom: 15px; }
        .detail-label { font-weight: bold; }
        .detail-value { color: #333; }
        .btn { display: inline-block; padding: 10px 15px; text-decoration: none; margin-right: 10px; border-radius: 4px; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-back { background-color: #808080; color: white; }
        button { cursor: pointer; }
    </style>
</head>
<body>
    <h1>Chi tiết sản phẩm</h1>

    <div class="product-detail">
        <div class="detail-row">
            <span class="detail-label">ID:</span>
            <span class="detail-value">${product.id}</span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Tên sản phẩm:</span>
            <span class="detail-value">${product.productName}</span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Giá:</span>
            <span class="detail-value">${product.price} VND</span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Mô tả:</span>
            <span class="detail-value">${product.description}</span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Số lượng:</span>
            <span class="detail-value">${product.quantity}</span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Category:</span>
            <span class="detail-value">${product.categoryName}</span>
        </div>

        <div style="margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/product?action=edit&id=${product.id}" class="btn btn-edit">✏️ Sửa</a>
            <a href="${pageContext.request.contextPath}/product?action=list" class="btn btn-back">← Quay lại</a>
        </div>
    </div>
</body>
</html>
