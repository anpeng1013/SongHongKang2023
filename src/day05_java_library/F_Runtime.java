package day05_java_library;

/**
 * java.lang.Runtime类：
 *      * 每个Java应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
 *      * public static Runtime getRuntime()： 返回与当前Java应用程序相关的运行时对象。应用程序不能创建自己的 Runtime 类实例。
 *      * public long totalMemory()：返回Java虚拟机中初始化时的内存总量。此方法返回的值可能随时间的推移而变化，默认为物理电脑内存的 1/64。
 *      * public long maxMemory()：返回 Java 虚拟机中最大程度能使用的内存总量。默认为物理电脑内存的 1/4。
 *      * public long freeMemory()：回 Java 虚拟机中的空闲内存量。调用gc方法可能导致 freeMemory 返回值的增加。
 *
 * @ClassName: F_Runtime.java
 * @Author: anpeng
 * @Date: 2023/11/17 17:24
 */
public class F_Runtime {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory(); //获取虚拟机初始时堆内存总量
        long maxMemory = runtime.maxMemory(); //获取虚拟机最大堆内存总量

        String str = "";
        //模拟占用内存
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
        long freeMemory = runtime.freeMemory(); //获取空闲堆内存总量
        System.out.println("总内存： " + initialMemory / 1024 / 1024 * 64 + "MB");
        System.out.println("最大可用内存： " + maxMemory / 1024 / 1024 * 4 + "MB");
        System.out.println("空闲内存： " + freeMemory / 1024 / 1024 + "MB") ;
        System.out.println("已用内存： " + (initialMemory-freeMemory) / 1024 / 1024 + "MB");
    }
}
