package test.test.decimal.util;

import java.math.BigDecimal;

public class ReDigitalUtil {

    public static final BigDecimal ZERO = new BigDecimal("0");

    public static BigDecimal add(Object dec1, Object dec2) {
        return dec1 == null && dec2 == null ? null : toBigDecimal(dec1).add(toBigDecimal(dec2));
    }

    public static BigDecimal subtract(Object dec1, Object dec2) {
        return dec1 == null && dec2 == null ? null : toBigDecimal(dec1).subtract(toBigDecimal(dec2));
    }

    public static BigDecimal toBigDecimal(Object obj) {
        if (obj == null) {
            return BigDecimal.ZERO;
        } else if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        } else if (obj instanceof Integer) {
            return new BigDecimal(((Integer) obj).toString());
        } else if (obj instanceof Long) {
            return new BigDecimal(((Long) obj).toString());
        } else if (obj instanceof Double) {
            Double dobj = (Double) obj;
            return !dobj.isInfinite() && !dobj.isNaN() ? BigDecimal.valueOf(dobj) : BigDecimal.ZERO;
        } else if (!(obj instanceof Float)) {
            if (obj.toString() == null) {
                return BigDecimal.ZERO;
            } else {
                String str = obj.toString().trim();
                if (str.toLowerCase().indexOf(101) > -1) {
                    try {
                        return new BigDecimal(str);
                    } catch (NumberFormatException var3) {
                        return BigDecimal.ZERO;
                    }
                } else {
                    if (str.indexOf(44) > -1) {
                        str = str.replace(",", "");
                    }

                    if (str.indexOf(32) > -1) {
                        str = str.replace(" ", "");
                    }

                    return str.matches("^[+-]?\\d+[\\.\\d]?\\d*+$") ? new BigDecimal(str) : BigDecimal.ZERO;
                }
            }
        } else {
            Float fobj = (Float) obj;
            return !fobj.isInfinite() && !fobj.isNaN() ? BigDecimal.valueOf((double) fobj) : BigDecimal.ZERO;
        }
    }

}
