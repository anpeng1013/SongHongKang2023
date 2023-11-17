package day05_java_library;

import java.util.Arrays;
import java.util.Random;

/**
 * java.util.Random: 用于产生随机数
 *      * boolean nextBoolean(): 返回下一个伪随机数，它是取自此随机数生成器序列的均匀分布的boolean值。
 *      * void nextBytes(byte[] bytes): 生成随机字节并将其置于用户提供的 byte 数组中。
 *      * double nextDouble():返回下一个伪随机数，它是取自此随机数生成器序列的、在0.0 和 1.0 之间均匀分布的 double 值。
 *      * float nextFloat():返回下一个伪随机数，它是取自此随机数生成器序列的、在0.0 和 1.0 之间均匀分布的 float 值。
 *      * double nextGaussian():返回下一个伪随机数，它是取自此随机数生成器序列的、呈高斯（“正态”）分布的double值，其平均值是0.0，标准差是1.0。
 *      * int nextInt():返回下一个伪随机数，它是此随机数生成器的序列中均匀分布的 int 值。
 *      * int nextInt(int n):返回一个伪随机数,它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
 *      * long nextLong():返回下一个伪随机数，它是取自此随机数生成器序列的均匀分布的long值。
 *
 * @ClassName: J_Random.java
 * @Author: anpeng
 * @Date: 2023/11/17 21:01
 */
public class J_Random {
    public static void main(String[] args) {
        Random rand = new Random();
        byte[] bytes = new byte[3];
        System.out.println("均匀分布的随机布尔值：" + rand.nextBoolean());
        System.out.println("均匀分布的随机整数：" + rand.nextInt());
        System.out.println("均匀分布的随机整数：" + rand.nextInt(100));//[0, 100)范围内的随机整数
        System.out.println("均匀分布的随机长整型：" + rand.nextLong());
        rand.nextBytes(bytes);
        System.out.println("均匀分布的随机字节数组：" + Arrays.toString(bytes));
        System.out.println("均匀分布的随机双精度浮点数：" + rand.nextDouble());//0.1815277588668539,最多16位小数，但第16位小数不是有效的
        System.out.println("均匀分布的随机单精度浮点数：" + rand.nextFloat());//0.13418919，最多8位小数，但第8位小数不是有效的
        System.out.println("高斯分布的随机双精度浮点数：" + rand.nextGaussian());//0.6901953776954831,最多16位小数，但第16位小数不是有效的
    }
}
