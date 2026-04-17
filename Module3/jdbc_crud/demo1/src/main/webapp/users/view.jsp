<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết người dùng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        .container { margin: 0 auto; }
        .user-detail { border: 1px solid #ddd; padding: 20px; border-radius: 5px; }
        .detail-row { margin-bottom: 15px; }
        .detail-label { font-weight: bold; display: inline-block; width: 120px; }
        .detail-value { color: #333; }
        .btn { display: inline-block; padding: 10px 15px; text-decoration: none; margin-right: 10px; border-radius: 4px; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-back { background-color: #808080; color: white; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chi tiết người dùng</h1>

        <div class="user-detail">
            <div class="detail-row">
                <span class="detail-label">ID:</span>
                <span class="detail-value">${user.id}</span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Họ tên:</span>
                <span class="detail-value">${user.fullName}</span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Email:</span>
                <span class="detail-value">${user.email}</span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Điện thoại:</span>
                <span class="detail-value">${user.phone}</span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Quốc gia:</span>
                <span class="detail-value">${user.country}</span>
            </div>

            <div class="detail-row">
                <span class="detail-label">Địa chỉ:</span>
                <span class="detail-value">${user.address}</span>
            </div>

            <div style="margin-top: 20px;">
                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}" class="btn btn-edit">✏️ Sửa</a>
                <a href="${pageContext.request.contextPath}/user?action=delete&id=${user.id}" class="btn btn-delete" onclick="return confirm('Bạn chắc chứ?')">🗑️ Xóa</a>
                <a href="${pageContext.request.contextPath}/user?action=list" class="btn btn-back">← Quay lại</a>
            </div>
        </div>
    </div>
</body>
</html>
