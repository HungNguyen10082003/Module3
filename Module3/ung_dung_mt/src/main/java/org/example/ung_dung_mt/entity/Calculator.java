package org.example.ung_dung_mt.entity;

public class Calculator {
    public static double calculate(double firstOperand, double secondOperand, String operator) throws Exception {
        switch (operator) {
            case "Addition":
                return firstOperand + secondOperand;
            case "Subtraction":
                return firstOperand - secondOperand;
            case "Multiplication":
                return firstOperand * secondOperand;
            case "Division":
                if (secondOperand == 0) {
                    throw new Exception("Không thể chia cho 0");
                }
                return firstOperand / secondOperand;
            default:
                throw new Exception("Phép toán không hợp lệ");
        }
    }
}