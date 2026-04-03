<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<Customer> customerList = new ArrayList<>();

    customerList.add(new Customer("Mai Văn Hoàn", "1983-08-20", "Hà Nội", "images/img1.jpg"));
    customerList.add(new Customer("Nguyễn Văn Nam", "1983-08-21", "Bắc Giang", "images/img2.jpg"));
    customerList.add(new Customer("Nguyễn Thái Hòa", "1983-08-22", "Nam Định", "images/img3.jpg"));
    customerList.add(new Customer("Trần Đăng Khoa", "1983-08-17", "Hà Tây", "images/img4.jpg"));
    customerList.add(new Customer("Nguyễn Đình Thi", "1983-08-19", "Hà Nội", "images/img5.jpg"));

    request.setAttribute("customerList", customerList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 70%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        img {
            width: 60px;
            height: 60px;
            object-fit: cover;
        }
    </style>
</head>
<body>

<h1>Danh sách khách hàng</h1>

<table>
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>

    <c:forEach var="c" items="${customerList}">
        <tr>
            <td>${c.name}</td>
            <td>${c.birthday}</td>
            <td>${c.address}</td>
            <td><img src="${c.image}" alt="Ảnh"></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>