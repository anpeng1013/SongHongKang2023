package day09_java_io;

import java_bean.day09.Logger;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * 1、标准输入、输出流（字节流）
 *      - System.in 和 System.out 分别代表了系统标准的输入和输出设备（默认输入设备是：键盘，输出设备是：显示器）
 *      - System.in 的类型是 InputStream。
 *      - System.out 的类型是 PrintStream，其是 OutputStream 的子类 FilterOutputStream 的子类。
 *      - 重定向：通过System类的setIn，setOut方法对默认输入输出设备进行修改
 *          -- public static void setIn(InputStream in)。
 *          -- public static void setOut(PrintStream out)。
 *      - System类中有三个常量对象：System.out, System.in, System.err。三个常量对象的声明如下：
 *          public final static InputStream in = null;
 *          public final static PrintStream out = null;
 *          public final static PrintStream err = null;
 *          --奇怪的是，常量对象用final修饰，但初始化却为null，常量对象名为什么用小写，且三个常量对象为何都有set方法？（常量对象按理不能被修改）
 *              答：final 声明的常量，表示在 Java 的语法体系中它们的值是不能修改的，而这三个常量对象的值是由 C/C++等系统函数进行初始化和修改值的，
 *              所以它们故意没有用大写，也有 set 方法。
 *
 * 2、打印流（可以是节点流，也是可以处理流）
 *      - 实现将基本数据类型的数据格式转化为字符串输出。
 *
 *      - 打印流： PrintStream（字节流） 和 PrintWriter（字符流）
 *          -- 提供一系列重载的print()和println方法，用于多种数据类型的输出。如：print（int x)
 *          -- PrintStream 和 PrintWriter 的输出不会抛出 IOException 异常。
 *          -- PrintStream 和 PrintWriter 有自动 flush 功能。
 *          -- PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。在需要写入字符而不是写入字节的情况下，应该使用 PrintWriter 类。
 *          -- System.out 返回的是 PrintStream 的实例。
 *
 *      - 构造器：
 *          -- PrintStream(File file) ：创建具有指定文件且不带自动行刷新的新打印流。
 *          -- PrintStream(File file, String csn)：创建具有指定文件名称和字符集且不带自动行刷新的新打印流。
 *          -- PrintStream(OutputStream out) ：创建新的打印流。
 *          -- PrintStream(OutputStream out, boolean autoFlush)：创建新的打印流。autoFlush 如果为 true，则每当写入 byte 数组、
 *              调用其中一个 println 方法或写入换行符或字节 ('\n') 时都会刷新输出缓冲区。
 *          -- PrintStream(OutputStream out, boolean autoFlush, String encoding) ：创建新的打印流
 *          -- PrintStream(String fileName)：创建具有指定文件名称且不带自动行刷新的新打印流。
 *          -- PrintStream(String fileName, String csn) ：创建具有指定文件名称和字符集且不带自动行刷新的新打印流。
 *
 * 3、扫描流：Scanner类
 *      - 构造方法：
 *          -- Scanner(File source) ：构造一个新的 Scanner，它生成的值是从指定文件扫描的。
 *          -- Scanner(File source, String charsetName) ：构造一个新的 Scanner，它生成的值是从指定文件扫描的，并指定字符编码。
 *          -- Scanner(InputStream source) ：构造一个新的 Scanner，它生成的值是从指定的输入流扫描的。
 *          -- Scanner(InputStream source, String charsetName) ：构造一个新的 Scanner，它生成的值是从指定的输入流扫描的，并指定字符编码。
 *
 *      - 常用方法：
 *          -- boolean hasNextXxx()：如果通过使用 nextXxx()方法，此扫描器输入信息中的下一个标记可以解释为默认基数中的一个 Xxx 值，则返回 true。
 *          -- Xxx nextXxx()：将输入信息的下一个标记扫描为一个 Xxx。
 *
 * @ClassName: H_OtherStreamTest.java
 * @Author: anpeng
 * @Date: 2024/3/22 22:17
 */
@SuppressWarnings("all")
public class H_StandardIOStreamTest {

    @Test
    public void testStandardIOStream(){
        //案例1：从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，直至当输入“e”或者“exit”时，退出程序。
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//把“标准”输入流（键盘输入）这个字节流转换成字符流，再包装成缓冲流
        String s = null;
        try {
            System.out.println("请输入信息：");
            while ((s = br.readLine()) != null){//读取用户输入的一行数据 --> 阻塞程序
                if("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)){
                    System.out.println("安全退出！");
                    break;
                }
                //将读取到的整行字符串转换成大写输出
                System.out.println("-->: " + s.toUpperCase());
                System.out.println("继续输入信息：");
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }finally {
            try {
                if (br != null){
                    br.close();
                }
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    @Test
    public void testPrintStream() throws FileNotFoundException {
        PrintStream ps = new PrintStream("file/io.txt");
        ps.println("anpeng love huli forever");
        ps.close();
        //测试日志工具类是否好用
        Logger.log("调用了System类的gc方法，建议启动垃圾回收");
        Logger.log("调用了TeamView的addMember()方法");
        Logger.log("用户尝试进行登录，验证失败");
    }

    @Test
    public void testScanner() throws FileNotFoundException {
        Scanner input = new Scanner(new FileInputStream("file/log.txt"));
        while (input.hasNextLine()){
            System.out.println(input.nextLine());
        }
        input.close();
    }

}
