package day05_java_library;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * java.math 包
 *      BigDecimal类
 *          * 一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。
 *
 *          * Float的精度为7位有效数字，Double的精度为15位有效数字。
 *
 *          * BigDecimal类支持不可变的、任意精度的有符号十进制定点数。
 *
 *          * 构造器：
 *              - public BigDecimal(double val)
 *              - public BigDecimal(String val) --> 推荐
 *
 *          * 常用方法：
 *              - public BigDecimal add(BigDecimal augend)
 *              - public BigDecimal subtract(BigDecimal subtrahend)
 *              - public BigDecimal multiply(BigDecimal multiplicand)
 *              - public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)：divisor是除数，scale指明保留几位小数，
 *                                          roundingMode指明舍入模式（ROUND_UP:向上加1、ROUND_DOWN:直接舍去、ROUND_HALF_UP:四舍五入）
 *
 * @ClassName: I_BigDecimal.java
 * @Author: anpeng
 * @Date: 2023/11/17 20:20
 */
public class I_BigDecimal {
    public static void main(String[] args) {
        BigInteger bi = new BigInteger("12433241123");
        BigDecimal bd = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);
        // System.out.println(bd.divide(bd2));
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));
    }
}
