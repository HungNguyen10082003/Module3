<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách người dùng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #2196F3; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin-right: 5px; text-decoration: none; border-radius: 3px; }
        .btn-add { background-color: #4CAF50; color: white; display: inline-block; margin-bottom: 20px; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-view { background-color: #008CBA; color: white; }
        .search-box { background: #f9f9f9; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .search-box input, .search-box select { padding: 8px; margin-right: 10px; border: 1px solid #ddd; border-radius: 3px; }
        .search-box button { padding: 8px 15px; background: #2196F3; color: white; border: none; border-radius: 3px; cursor: pointer; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>👥 Quản lý Người dùng</h1>
        
        <a href="${pageContext.request.contextPath}/users/add.jsp" class="btn btn-add">➕ Thêm người dùng</a>

        <div class="search-box">
            <form method="get" action="${pageContext.request.contextPath}/user">
                <input type="hidden" name="action" value="search">
                
                <select name="searchType">
                    <option value="name" <c:if test="${searchType != 'country'}">selected</c:if>>Tìm theo tên</option>
                    <option value="country" <c:if test="${searchType == 'country'}">selected</c:if>>Tìm theo quốc gia</option>
                </select>
                
                <c:if test="${searchType == 'country'}">
                    <select name="keyword">
                        <option value="">-- Chọn quốc gia --</option>
                        <c:forEach var="country" items="${countries}">
                            <option value="${country}" <c:if test="${country == keyword}">selected</c:if>>${country}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${searchType != 'country'}">
                    <input type="text" name="keyword" placeholder="Nhập tên người dùng..." value="${keyword}">
                </c:if>
                
                <button type="submit">🔍 Tìm kiếm</button>
            </form>
        </div>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Điện thoại</th>
                    <th>Quốc gia</th>
                    <th>Địa chỉ</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.fullName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.country}</td>
                        <td>${user.address}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user?action=view&id=${user.id}" class="btn btn-view">👁️ Xem</a>
                            <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}" class="btn btn-edit">✏️ Sửa</a>
                            <a href="${pageContext.request.contextPath}/user?action=delete&id=${user.id}" class="btn btn-delete" onclick="return confirm('Bạn chắc chứ?')">🗑️ Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty users}">
            <p style="text-align: center; color: red; margin-top: 20px;">Không có người dùng nào!</p>
        </c:if>
    </div>
</body>
</html>
