package day01_java_foundation;

/**
 * @ClassName: H_GetRandom.java
 * @Author: anpeng
 * @Date: 2023/4/6 10:57
 *
 * 获取一个指定范围的随机整数：
 *      1、调用Math类的random()，返回一个在[0,1)范围里的double
 *      2、Math.random()*100 --> 返回一个在[0,100)范围里的double
 *        (int)(Math.random()*100) --> 返回一个在[0,99]范围里的int
 *        (int)(Math.random()*100)+5 --> 返回一个在[5,104]范围里的
 *      3、获取[a,b]范围内的随机整数： (int)(Math.random()*(b-a+1))+a
 */
public class J_GetRandom {
    public static void main(String[] args) {
        double value = Math.random();
        System.out.println(value);
        //[50,100]
        int number = (int) (value * (100 - 50 + 1)) + 50;
        System.out.println(number);
    }
}
