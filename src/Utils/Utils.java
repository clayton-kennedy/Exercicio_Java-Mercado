package Utils;

import java.text.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {
    //formatar valor double em string
    static NumberFormat numberFormat = new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String doubleToString(double value) {
        return numberFormat.format(value);
    }
}
