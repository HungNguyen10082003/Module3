import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiscountCalculator {
    static class Entry {
        String name;
        double price;
        double pct;
        double discount;
        double finalPrice;
        LocalDateTime when;
        Entry(String name, double price, double pct) {
            this.name = name;
            this.price = price;
            this.pct = pct;
            this.discount = price * pct / 100.0;
            this.finalPrice = price - this.discount;
            this.when = LocalDateTime.now();
        }
        @Override public String toString() {
            return String.format("%s | %,.2f | %.2f%% | discount=%,.2f | final=%,.2f | %s",
                name, price, pct, discount, finalPrice, when.toString());
        }
        public String toCsv() {
            return String.format("\"%s\",%.2f,%.2f,%.2f,%.2f,%s",
                name.replace("\"","'"), price, pct, discount, finalPrice, when.toString());
        }
    }

    private final List<Entry> history = new ArrayList<>();

    public Entry calculate(String name, double price, double pct) {
        return new Entry(name == null || name.isEmpty() ? "(no name)" : name, price, pct);
    }

    public void saveToHistory(Entry e) {
        history.add(0, e);
    }

    public void exportCsv(String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, false))) {
            pw.println("name,price,percent,discount,final,when");
            for (Entry e : history) pw.println(e.toCsv());
        }
    }

    public void printHistory() {
        if (history.isEmpty()) { System.out.println("History trống."); return; }
        int i = 1;
        for (Entry e : history) {
            System.out.println((i++) + ". " + e);
        }
    }

    public static void main(String[] args) {
        DiscountCalculator app = new DiscountCalculator();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Discount Calculator ---");
            System.out.println("1. Tính");
            System.out.println("2. Lưu kết quả vào history");
            System.out.println("3. Xem history");
            System.out.println("4. Xuất CSV");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            String c = sc.nextLine().trim();
            try {
                switch (c) {
                    case "1": {
                        System.out.print("Tên sản phẩm: ");
                        String name = sc.nextLine().trim();
                        System.out.print("List price (VNĐ): ");
                        double price = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Discount (%): ");
                        double pct = Double.parseDouble(sc.nextLine().trim());
                        Entry e = app.calculate(name, price, pct);
                        System.out.printf("Discount: %,.2f VNĐ\nFinal price: %,.2f VNĐ\n", e.discount, e.finalPrice);
                        break;
                    }
                    case "2": {
                        System.out.print("Tên sản phẩm: ");
                        String name = sc.nextLine().trim();
                        System.out.print("List price (VNĐ): ");
                        double price = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Discount (%): ");
                        double pct = Double.parseDouble(sc.nextLine().trim());
                        Entry e = app.calculate(name, price, pct);
                        app.saveToHistory(e);
                        System.out.println("Đã lưu: " + e);
                        break;
                    }
                    case "3":
                        app.printHistory();
                        break;
                    case "4":
                        System.out.print("Đường dẫn file CSV (ví dụ history.csv): ");
                        String path = sc.nextLine().trim();
                        app.exportCsv(path);
                        System.out.println("Đã xuất: " + path);
                        break;
                    case "5":
                        System.out.println("Kết thúc.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Giá trị số không hợp lệ.");
            } catch (IOException ex) {
                System.out.println("Lỗi IO: " + ex.getMessage());
            }
        }
    }
}