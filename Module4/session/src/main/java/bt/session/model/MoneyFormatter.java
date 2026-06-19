package bt.session.model;

import java.util.Locale;

public final class MoneyFormatter {

    private MoneyFormatter() {
    }

    public static String format(long amount) {
        return String.format(Locale.US, "%,d", amount).replace(',', '.') + " đ";
    }
}