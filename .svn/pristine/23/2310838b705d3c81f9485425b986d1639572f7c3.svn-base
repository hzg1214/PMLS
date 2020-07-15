package cn.com.eju.deal.common.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtils {
    public static BigDecimal addToBigDecimal(BigDecimal d1, BigDecimal d2) {
        if (d1 == null) {
            d1 = BigDecimal.ZERO;
        }
        if (d2 == null) {
            d2 = BigDecimal.ZERO;
        }
        return d1.add(d2);
    }

    public static double scale(double value, int num) {
        BigDecimal b = new BigDecimal(String.valueOf(value));
        return b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String scale(String value, int num) {
        if (StringUtils.isEmpty(value)) {
            return value;
        } else {
            BigDecimal b = new BigDecimal(value);
            return b.setScale(num, BigDecimal.ROUND_HALF_UP).toString();
        }
    }

    public static BigDecimal divide(BigDecimal d1, BigDecimal d2) {
        if (d2 != null && d1 != null && d2.doubleValue() != 0) {
            return d1.divide(d2, 4, 1);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static double arithmetic(String exp) throws ArithmeticException {

        String result = parseExp(exp).replaceAll("[\\[\\]]", "");
        return Double.parseDouble(result);
    }

    public static double add(double x1, double x2) {
        BigDecimal y1 = new BigDecimal(x1);
        BigDecimal y2 = new BigDecimal(x2);
        BigDecimal result = y1.add(y2);
        return result.doubleValue();
    }

    public static double sub(double x1, double x2) {
        BigDecimal y1 = new BigDecimal(x1);
        BigDecimal y2 = new BigDecimal(x2);
        BigDecimal result = y1.subtract(y2);
        return result.doubleValue();
    }

    /**
     * 解析计算四则运算表达式，例：2+((3+4)*2-22)/2*3
     *
     * @param expression
     * @return
     * @throws Exception
     */
    private static String parseExp(String expression) throws ArithmeticException {
        expression = expression.replaceAll("\\s+", "").replaceAll(
                "^\\((.+)\\)$", "$1");
        String minExp = "^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$";
        // 最小表达式计算
        if (expression.matches(minExp)) {
            String result = calculate(expression);
            return Double.parseDouble(result) >= 0 ? result : "[" + result
                    + "]";
        }
        // 计算不带括号的四则运算
        String noParentheses = "^[^\\(\\)]+$";
        String priorOperatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
        String operatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
        if (expression.matches(noParentheses)) {
            Pattern patt = Pattern.compile(priorOperatorExp);
            Matcher mat = patt.matcher(expression);
            if (mat.find()) {
                String tempMinExp = mat.group();
                expression = expression.replaceFirst(priorOperatorExp,
                        parseExp(tempMinExp));
            } else {
                patt = Pattern.compile(operatorExp);
                mat = patt.matcher(expression);

                if (mat.find()) {
                    String tempMinExp = mat.group();
                    expression = expression.replaceFirst(operatorExp,
                            parseExp(tempMinExp));
                }
            }
            return parseExp(expression);
        }
        // 计算带括号的四则运算
        String minParentheses = "\\([^\\(\\)]+\\)";
        Pattern patt = Pattern.compile(minParentheses);
        Matcher mat = patt.matcher(expression);
        if (mat.find()) {
            String tempMinExp = mat.group();
            expression = expression.replaceFirst(minParentheses,
                    parseExp(tempMinExp));
        }
        return parseExp(expression);
    }

    /**
     * 计算最小单位四则运算表达式（两个数字）
     *
     * @param exp
     * @throws Exception
     */
    private static String calculate(String exp) throws ArithmeticException {
        exp = exp.replaceAll("[\\[\\]]", "");
        String number[] = exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(
                ",");
        BigDecimal number1 = new BigDecimal(number[0]);
        BigDecimal number2 = new BigDecimal(number[1]);
        BigDecimal result = null;

        String operator = exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1");
        if ("+".equals(operator)) {
            result = number1.add(number2);
        } else if ("-".equals(operator)) {
            result = number1.subtract(number2);
        } else if ("*".equals(operator)) {
            result = number1.multiply(number2);
        } else if ("/".equals(operator)) {
            if (number2.toBigInteger().intValue() == 0) {
                throw new ArithmeticException("除数为0");
            }
            result = number1.divide(number2, 20, RoundingMode.HALF_UP);
        }
        return result != null ? result.toString() : null;
    }
}
