package day03_java_exception;

import org.junit.Test;

import java.util.Scanner;

/**
 * 异常：指的是程序在执行过程中出现的非正常情况，如果不处理最终会导致JVM的非正常停止。如输入格式问题，读取文件是否存在，网络是否通畅等。
 *      注意：异常指的并不是语法错误和代码逻辑错误。如果语法错了，编译不通过，不会产生字节码文件，根本不能运行。代码逻辑错误，只是没有
 *      得到想要的结果，如：求a与b的和，结果写成了a-b。
 *
 *      抛出机制：Java中把不同的异常用不同的类表示，一旦发生某种异常，就创建该异常类型的对象，并且抛出(throw）。然后程序员可以
 *              捕获(catch)到这个异常对象，并处理；如果没有捕获(catch)这个异常对象，那么这个异常对象将会导致程序终止。
 *
 *      如何处理：对于程序出现的异常，一般有两种解决方法。
 *          1、遇到错误就终止程序的运行。
 *          2、另一种方法是程序员在编写程序时，就充分考虑到各种可能发生的异常和错误，极力预防和避免。实在无法避免的，要编写相应的代码
 *              进行异常的检测、以及异常的处理，保证代码的健壮性。
 *
 * 异常体系：
 *      Throwable
 *      常用方法：
 *          public void printStackTrace()：打印异常的详细信息。包含了异常的类型、异常的原因、异常出现的位置。
 *                                          在开发和调试阶段都得使用printStackTrace。
 * *        public String getMessage()：获取发生异常的原因。
 *
 *      Error和Exception
 *          Throwable可分为两类：Error和Exception。分别对应着java.lang.Error与java.lang.Exception 两个类。
 *
 *          Error：Java虚拟机无法解决的严重问题。如： JVM系统内部错误、资源耗尽等严重情况。一般不编写针对性的代码进行处理。
 *              例如：StackOverflowError（栈内存溢出）和 OutOfMemoryError（堆内存溢出，简称OOM）
 *
 *          Exception：其它因编程错误或偶然的外在因素导致的一般性问题，需要使用针对性的代码进行处理，使程序继续运行。
 *              否则一旦发生异常，程序也会挂掉。
 *              例如：
 *                  空指针访问
 *                  试图读取不存在的文件
 *                  网络连接中断
 *                  数组角标越界
 *
 *          说明：
 *              无论是 Error 还是 Exception，还有很多子类，异常的类型非常丰富。当代码运行出现异常时，特别是我们不熟悉的异常时，
 *              不要紧张，把异常的简单类名，拷贝到API中去查去认识它即可。 通常讲的异常处理，其实针对的就是Exception。
 *
 * 常见的错误Error
 *      最常见的就是VirtualMachineError，它有两个经典的子类：StackOverflowError、OutOfMemoryError
 *
 * 编译时异常和运行时异常
 *      Java程序的执行分为编译时过程和运行时过程。有的错误只有在运行时才会发生，比如：除数为0，数组下标越界等。因此，根据
 *      异常可能出现的阶段，可以将异常分为：
 *          * 编译时期异常（即checked异常、受检异常）：在代码编译阶段，编译器就能明确警示当前代码可能发生（不是一定发生）xx异常，并明确督促
 *              程序员提前编写处理它的代码。如果程序员没有编写对应的异常处理代码，则编译器就会直接判定编译失败，从而不能生成字节码文件。通常，
 *              这类异常的发生不是由程序员的代码引起的，或者不是靠加简单判断就可以避免的，例如：FileNotFoundException（文件找不到异常）。
 *
 *          * 运行时期异常（即runtime异常、unchecked异常、非受检异常）：在代码编译阶段，编译器完全不做任何检查，无论该异常是否会发生，
 *              编译器都不给出任何提示。只有等代码运行起来并确实发生了xx异常，它才能被发现。通常，这类异常是由程序员的代码编写不当引起的，
 *              只要稍加判断，或者细心检查就可以避免。java.lang.RuntimeException:类及它的子类都是运行时异常。比如：
 *              ArrayIndexOutOfBoundsException:数组下标越界异常， ClassCastException类型转换异常。
 *
 * @ClassName: A_Exception.java
 * @Author: anpeng
 * @Date: 2023/11/6 16:36
 */
@SuppressWarnings("all")
public class A_ExceptionTest {
    //常见的错误Error1-StackOverflowError
    @Test
    public void test01() {//java.lang.StackOverflowError 栈溢出错误
        recursion();
    }

    @SuppressWarnings("all")
    public void recursion() {//递归方法
        recursion();
    }

    //常见的错误Error2-OutOfMemoryError
    @Test
    @SuppressWarnings("all")
    public void test02() {//java.lang.OutOfMemoryError: Java heap space  堆(内存)溢出错误
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            stringBuilder.append("anpeng");
        }
    }

    //运行时异常(非受检异常)
    @Test
    public void test03() {//java.lang.NullPointerException: 空指针异常
        int[][] arr = new int[3][];
        System.out.println(arr[0].length);
    }

    @Test
    @SuppressWarnings("all")
    public void test04() {//java.lang.ClassCastException: 类型转换异常
        Object obj = 15;
        String str = (String) obj;
    }

    @Test
    @SuppressWarnings("all")
    public void test05() {//java.lang.ArrayIndexOutOfBoundsException: 数组索引越界异常
        int[] arr = new int[5];
        for (int i = 1; i <= 5; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test06() {//java.util.InputMismatchException 输入不匹配异常
        Scanner input = new Scanner(System.in);
        System.out.print("请输入一个整数： ");//输入非整数时抛出异常
        @SuppressWarnings("unused")
        int num = input.nextInt();
        input.close();
    }

    @Test
    @SuppressWarnings("all")
    public void test07() {//java.lang.ArithmeticException: / by zero 算术异常
        int a = 1;
        int b = 0;
        System.out.println(a / b);
    }

    //编译时异常(受检异常)
    @Test
    public void test08(){//java.lang.InterruptedException 中断异常
        //Thread.sleep(1000); //休眠1秒
    }

    @Test
    public void test09(){//ClassNotFoundException 类未找到异常
        //Class c = Class.forName("java.lang.String");
        //System.out.println(c);
    }

    @Test
    public void test10(){//java.sql.SQLException SQL异常
        //Connection conn = DriverManager.getConnection("...");
    }

    @Test
    public void test11(){//java.io.FileNotFoundException 文件未找到异常
        //FileInputStream fis = new FileInputStream("char.txt");
    }

}
