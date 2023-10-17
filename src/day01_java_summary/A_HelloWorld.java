package day01_java_summary;

/**
 * 总结：
 * 1.Java程序编写和执行的过程:
 *      步骤1：编写。将Java代码编写在.java结尾的源文件中
 *                 说明：
 *                     ① class：关键字，表示类，后面跟着类名
 *                     ② main()方法表示程序入口，格式是固定的：public static void main(String[] args)。务必记住！
 *                        唯一能变的是，参数写法和参数名：String[] args 改为 String args[] 或 String[] arguments
 *                     ③ Java程序，是严格区分大小写的
 *                     ④ 每一行执行语句必须以引号；结束
 *      步骤2：编译。针对.java结尾的源文件进行编译。 格式：javac 源文件名.java
 *                  说明：
 *                      ① 编译以后会生成1个或多个字节码文件。每一个字节码文件对应一个Java类，并且字节码文件名与类名相同。
 *      步骤3：运行。针对编译后生成的字节码文件，进行解释运行。 格式：java 字节码文件名
 *                  说明：
 *                      ① 一个源文件中可以声明多个类，但最多只能有一个类使用public进行声明。
 *                         且要求声明为public的类的名称要与源文件名相同。
 */
public class A_HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
