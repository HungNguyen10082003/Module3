package ss8.customermanagement.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// Tiện ích đọc/ghi CSV đơn giản
public class ReadWriteCsv {
    public static List<String> readAllLines(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    public static void writeAllLines(String path, List<String> lines) throws IOException {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }
}
