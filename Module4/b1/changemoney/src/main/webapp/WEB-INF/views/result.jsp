<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kết Quả Chuyển Đổi</title>
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
            max-width: 400px;
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
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            text-align: center;
        }
        .result-label {
            font-size: 14px;
            opacity: 0.9;
            margin-bottom: 10px;
        }
        .result-value {
            font-size: 32px;
            font-weight: bold;
            margin: 10px 0;
        }
        .currency {
            font-size: 18px;
            opacity: 0.9;
        }
        .info {
            background: #f0f4ff;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
            border-left: 4px solid #667eea;
        }
        .info-row {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
            color: #555;
        }
        .info-label {
            font-weight: bold;
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
        .btn-convert {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-convert:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .btn-back {
            background: #e0e0e0;
            color: #333;
        }
        .btn-back:hover {
            background: #d0d0d0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>✅ Kết Quả Chuyển Đổi</h1>

    <div class="result-box">
        <div class="result-label">Số tiền USD:</div>
        <div class="result-value">$<%= String.format("%.2f", request.getAttribute("amount")) %></div>
        <div class="currency">USD</div>
    </div>

    <div style="text-align: center; font-size: 24px; margin: 10px 0;">⬇️</div>

    <div class="result-box">
        <div class="result-label">Số tiền VND:</div>
        <div class="result-value"><%= String.format("%,.0f", request.getAttribute("result")) %></div>
        <div class="currency">VND</div>
    </div>

    <div class="info">
        <div class="info-row">
            <span class="info-label">Tỉ giá:</span>
            <span>1 USD = <%= String.format("%,.0f", request.getAttribute("rate")) %> VND</span>
        </div>
        <div class="info-row">
            <span class="info-label">Ngày:</span>
            <span><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()) %></span>
        </div>
    </div>

    <div class="button-group">
        <form method="GET" action="/" style="flex: 1;">
            <button class="btn-convert" type="submit">Chuyển Đổi Lại</button>
        </form>
    </div>
</div>
</body>
</html>
