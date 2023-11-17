package day05_java_library;

import java.math.BigInteger;

/**
 * java.math 包
 *      BigInteger
 *          * Integer 类作为 int 的包装类，能存储的最大整型值为 2^31-1， Long 类也是有限的，最大为 2^63-1。如果要表示再大的整数，
 *              不管是基本数据类型还是他们的包装类都无能为力，更不用说进行运算了。
 *
 *          * java.math 包的 BigInteger 可以表示不可变的任意精度的整数。 BigInteger 提供所有Java的基本整数操作符的对应物，并提供
 *              java.lang.Math 的所有相关方法。另外，BigInteger 还提供以下运算：模算术、 GCD 计算、质数测试、素数生成、位操作以及一些其他操作。
 *
 *          * 构造器
 *              - BigInteger(String val)：根据字符串构建 BigInteger 对象
 *
 *          * 方法
 *              - public BigInteger abs()：返回此 BigInteger 的绝对值的 BigInteger。
 *              - BigInteger add(BigInteger val) ：返回其值为 (this + val) 的 BigInteger。
 *              - BigInteger subtract(BigInteger val) ：返回其值为 (this - val) 的 BigInteger。
 *              - BigInteger multiply(BigInteger val) ：返回其值为 (this * val) 的 BigInteger。
 *              - BigInteger divide(BigInteger val) ：返回其值为 (this / val) 的 BigInteger。整数相除只保留整数部分。
 *              - BigInteger remainder(BigInteger val) ：返回其值为 (this % val) 的 BigInteger。取余
 *              - BigInteger[] divideAndRemainder(BigInteger val)：返回包含(this / val)后跟(this % val)的两个BigInteger的数组。
 *              - BigInteger pow(int exponent) ：返回其值为 (this^exponent) 的 BigInteger。次幂
 *
 * @ClassName: H_BigInteger.java
 * @Author: anpeng
 * @Date: 2023/11/17 20:06
 */
public class H_BigInteger {
    public static void main(String[] args) {
        //long bigNum = 123456789123456789123456789L;//long长整型无法表示这么大的整数。
        BigInteger b1 = new BigInteger("12345678912345678912345678");
        BigInteger b2 = new BigInteger("78923456789123456789123456789");
        //System.out.println("和： " + (b1+b2));//错误的，无法直接使用加号+进行求和
        System.out.println("和： " + b1.add(b2));
        System.out.println("减： " + b1.subtract(b2));
        System.out.println("乘： " + b1.multiply(b2));
        System.out.println("除： " + b2.divide(b1));
        System.out.println("余： " + b2.remainder(b1));
    }
}
