<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>

<form action="calculate" method="post">
    <fieldset style="width: 300px">
        <legend>Calculator</legend>

        <table>
            <tr>
                <td>First operand:</td>
                <td><input type="text" name="firstOperand" required></td>
            </tr>
            <tr>
                <td>Operator:</td>
                <td>
                    <select name="operator">
                        <option value="Addition">Addition</option>
                        <option value="Subtraction">Subtraction</option>
                        <option value="Multiplication">Multiplication</option>
                        <option value="Division">Division</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Second operand:</td>
                <td><input type="text" name="secondOperand" required></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Calculate</button></td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>