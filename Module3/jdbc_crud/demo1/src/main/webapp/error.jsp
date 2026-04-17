<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lỗi</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 20px; }
        .error-container { max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); border-left: 4px solid #dc3545; }
        h1 { color: #dc3545; margin-bottom: 10px; }
        p { color: #666; margin-bottom: 20px; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>⚠️ Có lỗi xảy ra</h1>
        <p>Xin lỗi, đã có lỗi trong quá trình xử lý yêu cầu của bạn.</p>
        <p>
            <strong>Mã lỗi:</strong> ${pageContext.errorData.statusCode}<br>
            <strong>Thông báo:</strong> ${pageContext.errorData.throwable.message}
        </p>
        <p><a href="/">← Quay lại trang chủ</a></p>
    </div>
</body>
</html>
