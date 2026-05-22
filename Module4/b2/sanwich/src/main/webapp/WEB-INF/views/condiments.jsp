<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sandwich Condiments</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        h1 { font-size: 36px; }
        .condiments { font-size: 24px; }
        .save { margin-top: 20px; }
    </style>
</head>
<body>
<h1>Sandwich Condiments</h1>

<form action="${pageContext.request.contextPath}/save" method="post">
    <div class="condiments">
        <label><input type="checkbox" name="condiment" value="Lettuce"> Lettuce</label>
        <label><input type="checkbox" name="condiment" value="Tomato"> Tomato</label>
        <label><input type="checkbox" name="condiment" value="Mustard"> Mustard</label>
        <label><input type="checkbox" name="condiment" value="Sprouts"> Sprouts</label>
    </div>

    <div class="save">
        <button type="submit">Save</button>
    </div>
</form>

</body>
</html>
