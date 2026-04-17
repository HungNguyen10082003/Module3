<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f6f8fb; }
        .container { max-width: 1200px; margin: 0 auto; }
        h1 { margin-bottom: 20px; }
        .top-actions { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; flex-wrap: wrap; gap: 10px; }
        .search-box { background: #fff; border: 1px solid #ddd; padding: 16px; border-radius: 8px; margin-bottom: 18px; }
        .search-row { display: grid; grid-template-columns: 2fr 1fr auto; gap: 10px; }
        input[type="text"], select { padding: 9px; border: 1px solid #ccc; border-radius: 5px; width: 100%; }
        button, .btn { border: none; padding: 9px 14px; border-radius: 5px; cursor: pointer; text-decoration: none; display: inline-block; }
        .btn-add { background-color: #2e7d32; color: white; }
        .btn-categories { background-color: #6a1b9a; color: white; }
        .btn-edit { background-color: #1565c0; color: white; }
        .btn-delete { background-color: #c62828; color: white; }
        .btn-view { background-color: #00838f; color: white; }
        .table-wrap { background: #fff; border-radius: 8px; border: 1px solid #ddd; overflow: auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border-bottom: 1px solid #eee; padding: 10px; text-align: left; }
        th { background-color: #1f2937; color: white; }
        .pagination { margin-top: 16px; display: flex; gap: 6px; align-items: center; flex-wrap: wrap; }
        .pagination a, .pagination span { padding: 7px 11px; border: 1px solid #ccc; border-radius: 5px; text-decoration: none; color: #222; background: #fff; }
        .pagination .active { background: #1f2937; color: #fff; border-color: #1f2937; }
        .empty { text-align: center; color: #c62828; margin-top: 14px; }
        .modal { display: none; position: fixed; z-index: 10; left: 0; top: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.45); }
        .modal-content { background: white; margin: 11% auto; padding: 20px; border-radius: 8px; max-width: 500px; }
        .modal-actions { display: flex; justify-content: flex-end; gap: 10px; margin-top: 18px; }
        .btn-cancel { background: #6b7280; color: white; }
        .btn-confirm { background: #c62828; color: white; }
        .actions { display: flex; gap: 6px; flex-wrap: wrap; }
        @media (max-width: 768px) {
            .search-row { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Quản lý sản phẩm</h1>

    <div class="top-actions">
        <div>
            <a href="${pageContext.request.contextPath}/product?action=add" class="btn btn-add">Thêm sản phẩm mới</a>
            <a href="${pageContext.request.contextPath}/product?action=categories" class="btn btn-categories">Category và sản phẩm</a>
        </div>
    </div>

    <div class="search-box">
        <form method="get" action="${pageContext.request.contextPath}/product">
            <input type="hidden" name="action" value="list">
            <div class="search-row">
                <input type="text" name="keyword" placeholder="Tìm theo tên sản phẩm" value="${keyword}">
                <select name="categoryId">
                    <option value="">Tất cả category</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" ${selectedCategoryId == category.id ? 'selected' : ''}>${category.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Tìm kiếm</button>
            </div>
        </form>
    </div>

    <div class="table-wrap">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên sản phẩm</th>
                    <th>Category</th>
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
                        <td>${product.categoryName}</td>
                        <td>${product.price} USD</td>
                        <td>${product.description}</td>
                        <td>${product.quantity}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/product?action=view&id=${product.id}" class="btn btn-view">Xem</a>
                            <a href="${pageContext.request.contextPath}/product?action=edit&id=${product.id}" class="btn btn-edit">Sửa</a>
                            <button type="button" class="btn btn-delete" onclick="openDeleteModal('${product.id}')">Xóa</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${empty products}">
        <p class="empty">Không có sản phẩm nào phù hợp.</p>
    </c:if>

    <c:if test="${totalPages > 0}">
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/product?action=list&page=${currentPage - 1}&keyword=${keyword}&categoryId=${selectedCategoryId}">Trước</a>
            </c:if>

            <c:forEach var="pageNumber" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${pageNumber == currentPage}">
                        <span class="active">${pageNumber}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/product?action=list&page=${pageNumber}&keyword=${keyword}&categoryId=${selectedCategoryId}">${pageNumber}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/product?action=list&page=${currentPage + 1}&keyword=${keyword}&categoryId=${selectedCategoryId}">Sau</a>
            </c:if>
        </div>
    </c:if>
</div>

<div id="deleteModal" class="modal">
    <div class="modal-content">
        <h3>Xác nhận xóa sản phẩm</h3>
        <p>Bạn có chắc chắn muốn xóa sản phẩm có ID <strong id="deleteProductIdLabel"></strong>?</p>
        <form method="post" action="${pageContext.request.contextPath}/product">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" id="deleteProductId">
            <div class="modal-actions">
                <button type="button" class="btn btn-cancel" onclick="closeDeleteModal()">Hủy</button>
                <button type="submit" class="btn btn-confirm">Xóa</button>
            </div>
        </form>
    </div>
</div>

<script>
    function openDeleteModal(id) {
        document.getElementById('deleteProductId').value = id;
        document.getElementById('deleteProductIdLabel').textContent = id;
        document.getElementById('deleteModal').style.display = 'block';
    }

    function closeDeleteModal() {
        document.getElementById('deleteModal').style.display = 'none';
    }

    window.onclick = function(event) {
        const modal = document.getElementById('deleteModal');
        if (event.target === modal) {
            closeDeleteModal();
        }
    };
</script>
</body>
</html>
