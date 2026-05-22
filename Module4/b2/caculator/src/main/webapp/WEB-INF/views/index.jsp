<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Caculator</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 24px; }
        h1 { font-size: 48px; font-family: Georgia, serif; }
        .inputs { margin: 16px 0; }
        input[type=text] { width: 220px; padding: 8px; margin-right: 16px; }
        button { padding: 10px 18px; margin-right: 8px; }
    </style>
</head>
<body>
<h1>Caculator</h1>

<form method="post" action="calculate">
    <div class="inputs">
        <input type="text" name="a" value="12" />
        <input type="text" name="b" value="12" />
    </div>

    <div>
        <button type="submit" name="op" value="+">Addition(+)</button>
        <button type="submit" name="op" value="-">Subtraction(-)</button>
        <button type="submit" name="op" value="x">Multiplication(X)</button>
        <button type="submit" name="op" value="/">Division(/)</button>
    </div>
</form>

</body>
</html>
