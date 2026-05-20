<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kết Quả Tra Cứu Từ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            max-width: 500px;
            width: 100%;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-top: 0;
        }
        .result-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 25px;
            border-radius: 8px;
            margin: 20px 0;
            text-align: center;
        }
        .keyword {
            font-size: 18px;
            opacity: 0.9;
            margin-bottom: 10px;
        }
        .keyword-text {
            font-size: 32px;
            font-weight: bold;
            margin: 10px 0;
            text-transform: lowercase;
        }
        .meaning-label {
            font-size: 14px;
            opacity: 0.9;
            margin-top: 15px;
        }
        .meaning-text {
            font-size: 24px;
            font-weight: bold;
            margin-top: 8px;
        }
        .not-found {
            background: #f8d7da;
            color: #721c24;
            padding: 25px;
            border-radius: 8px;
            text-align: center;
            margin: 20px 0;
            border: 2px solid #f5c6cb;
        }
        .not-found-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .not-found-message {
            font-size: 16px;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        button {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s;
        }
        .btn-search {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-search:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .btn-home {
            background: #e0e0e0;
            color: #333;
        }
        .btn-home:hover {
            background: #d0d0d0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🔍 Kết Quả Tra Cứu</h1>

    <% if ((Boolean) request.getAttribute("found")) { %>
    <!-- Tìm thấy từ -->
    <div class="result-box">
        <div class="keyword">Từ tiếng Anh:</div>
        <div class="keyword-text"><%= request.getAttribute("keyword") %></div>
        <div class="meaning-label">Nghĩa tiếng Việt:</div>
        <div class="meaning-text"><%= request.getAttribute("meaning") %></div>
    </div>
    <% } else { %>
    <!-- Không tìm thấy từ -->
    <div class="not-found">
        <div class="not-found-title">❌ Không Tìm Thấy</div>
        <div class="not-found-message">
            Từ "<strong><%= request.getAttribute("keyword") %></strong>" không có trong từ điển.<br>
            Vui lòng kiểm tra lại chính tả hoặc thử từ khác.
        </div>
    </div>
    <% } %>

    <div class="button-group">
        <form method="GET" action="/dictionary" style="flex: 1;">
            <button class="btn-search" type="submit">🔍 Tra Cứu Lại</button>
        </form>
        <form method="GET" action="/" style="flex: 1;">
            <button class="btn-home" type="submit">🏠 Trang Chủ</button>
        </form>
    </div>
</div>
</body>
</html>
