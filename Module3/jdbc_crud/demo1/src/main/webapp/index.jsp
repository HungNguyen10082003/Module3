<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Hệ thống quản lý</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; padding: 40px 20px; }
        .container { max-width: 1000px; margin: 0 auto; }
        h1 { color: white; text-align: center; margin-bottom: 40px; font-size: 32px; }
        .modules { display: grid; grid-template-columns: repeat(auto-fit, minmax(360px, 1fr)); gap: 20px; justify-content: center; }
        .module { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2); }
        .module h2 { color: #333; margin-bottom: 15px; }
        .module p { color: #666; margin-bottom: 20px; }
        .module ul { list-style: none; margin-bottom: 20px; }
        .module li { padding: 8px 0; padding-left: 20px; color: #555; }
        .module li:before { content: "✓ "; color: #667eea; font-weight: bold; margin-right: 10px; }
        .module a { display: inline-block; background-color: #667eea; color: white; padding: 12px 25px; text-decoration: none; border-radius: 5px; transition: background 0.3s; }
        .module a:hover { background-color: #764ba2; }
        .icon { font-size: 40px; margin-bottom: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎯 Hệ thống quản lý</h1>

        <div class="modules">
            <!-- Product Management -->
            <div class="module">
                <div class="icon">📦</div>
                <h2>Quản lý Sản phẩm</h2>
                <p>Ứng dụng quản lý sản phẩm có kết nối DB với category</p>
                <ul>
                    <li>Danh sách sản phẩm có phân trang</li>
                    <li>Thêm sản phẩm mới</li>
                    <li>Chỉnh sửa thông tin</li>
                    <li>Xóa sản phẩm bằng modal</li>
                    <li>Tìm kiếm theo tên và category</li>
                    <li>Danh sách category và sản phẩm theo category</li>
                </ul>
                <a href="${pageContext.request.contextPath}/product?action=list">Truy cập →</a>
            </div>
        </div>
    </div>
</body>
</html>
