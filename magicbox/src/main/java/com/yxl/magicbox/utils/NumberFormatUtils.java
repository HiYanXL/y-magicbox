package com.yxl.magicbox.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtils {
    private static final String defaultPattern = "#,###,##0.00";

    /**
     * 对数字进行格式化
     * 先判断str是否为Numeric
     * @param str
     * @return
     */
    public static String format(String str) {
        return format(str,true);
    }

    /**
     * 对数字进行格式化
     * @param str
     * @param b 默认true,对str格式化之前先判断是否为Numeric；若为false,直接格式化str,对于0.或者.0这些值也能格式化
     * @return
     */
    public static String format(@NotNull String str,boolean b) {
        if(b){
            if (isNotNumber(str)) {
                return null;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat(defaultPattern);
        try {
            return decimalFormat.format(new BigDecimal(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean isNumber(@NotNull String number) {
        if (number == null) {
            return false;
        }
        int index = number.indexOf(".");
        if (index < 0) {
            return StringUtils.isNumeric(number);
        } else {
            String num1 = number.substring(0, index);
            String num2 = number.substring(index + 1);
            return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
        }
    }

    public static boolean isNotNumber(@NotNull String number) {
        return !isNumber(number);
    }
}