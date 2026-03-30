<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Discount Calculator</title>
</head>
<body>
<h2>Product Discount Calculator</h2>

<form action="display-discount" method="post">
    <label>Product Description:</label><br>
    <input type="text" name="description"><br><br>

    <label>List Price:</label><br>
    <input type="text" name="price"><br><br>

    <label>Discount Percent (%):</label><br>
    <input type="text" name="percent"><br><br>

    <input type="submit" value="Calculate Discount">
</form>
</body>
</html>