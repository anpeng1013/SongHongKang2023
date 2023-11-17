package day05_java_library;

import java_bean.day05.GCDemo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  java.lang.System类:
 *      * System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于java.lang包。
 *
 *      * 由于该类的构造器是private的，所以无法创建该类的对象。其内部的成员变量和成员方法都是static的，所以也可以很方便的进行调用。
 *
 *      * 成员变量，如 Scanner scan = new Scanner(System.in);
 *          - System类内部包含 in、 out 和 err 三个成员变量，分别代表标准输入流(键盘输入)，标准输出流(显示器)和标准错误输出流(显示器)。
 *
 *      * 成员方法
 *          - native long currentTimeMillis()：该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)
 *                                              1970年1月1号0时0分0秒所差的毫秒数。
 *
 *          - void exit(int status)：该方法的作用是退出程序。其中status的值为0代表正常退出，非零代表异常退出。使用该方法可以在图形界面
 *                                  编程中实现程序的退出功能等。
 *
 *          - void gc()：该方法的作用是请求系统进行垃圾回收。至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况。
 *
 *          - String getProperty(String key)：该方法的作用是获得系统中属性名为key的属性对应的值。系统中常见的属性名以及属性的作用如下表所示：
 *                  属性名key                      属性说明
 *                  java.version                  java运行时环境版本
 *                  java.home                     java安装目录
 *                  os.name                       操作系统的名称
 *                  os.version                    操作系统的版本
 *                  user.name                     用户的账户名称
 *                  user.home                     用户的主目录
 *                  user.dir                      用户的当前工作目录
 *
 *          - static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)：从指定源数组中复制一个数组，
 *                                                              复制从指定的位置开始，到目标数组的指定位置结束。常用于数组的插入和删除。
 *
 *
 * @ClassName: E_SystemTest.java
 * @Author: anpeng
 * @Date: 2023/11/17 16:39
 */
@SuppressWarnings("all")
public class E_SystemTest {
    @Test
    public void test01(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的名字：");
        String input = scanner.next();
        System.out.println(input);
    }

    @Test
    public void test02(){
        long time = System.currentTimeMillis();
        System.out.println("现在的系统时间距离 1970 年 1 月 1 日凌晨： " + time + "毫秒");
        System.exit(0);
        System.out.println("over");//不会执行
    }

    @Test
    public void test03(){
        String javaVersion = System.getProperty("java.version");
        System.out.println("java运行时环境版本: " + javaVersion);
        String javaHome = System.getProperty("java.home");
        System.out.println("java安装目录: " + javaHome);
        String osName = System.getProperty("os.name");
        System.out.println("操作系统的名称: " + osName);
        String osVersion = System.getProperty("os.version");
        System.out.println("操作系统的版本: " + osVersion);
        String userName = System.getProperty("user.name");
        System.out.println("用户的账户名称: " + userName);
        String userHome = System.getProperty("user.home");
        System.out.println("用户的主目录: " + userHome);
        String userDir = System.getProperty("user.dir");
        System.out.println("用户的当前工作目录: " + userDir);
    }

    @Test
    public void test04() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            GCDemo gcDemo = new GCDemo(i);//每一次循环gcDemo就会指向新的对象，那么上次的对象就没有变量引用它了，就成垃圾对象。
        }

        System.gc();//如果不调用这句代码，GC可能不工作，因为当前内存很充足，GC就觉得不着急回收垃圾对象。调用这句代码，会让GC尽快来工作。

        Thread.sleep(5000);//为了看到垃圾回收器工作，让main方法不那么快结束，因为main结束就会导致JVM退出，GC也会跟着结束
    }

    @Test
    public void test05(){
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = new int[10];
        System.arraycopy(arr1,0,arr2,3,arr1.length);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void test06(){
        int[] arr = {1,2,3,4,5};
        System.arraycopy(arr,0,arr,1,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test07(){
        int[] arr = {1,2,3,4,5};
        System.arraycopy(arr,1,arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }


}
