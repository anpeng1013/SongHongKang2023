package day01_java_foundation;

//①导包
import java.util.Scanner;

/**
 * @ClassName: F_KeyboardInput.java
 * @Author: anpeng
 * @Date: 2023/4/6 9:41
 *
 * Scanner类：一个简单的文本扫描器，可以使用正则表达式解析原始类型和字符串。
 *
 * 获取键盘输入步骤
 *      1、导包：import java.util.Scanner;
 *      2、创建Scanner类型的对象：Scanner scanner = new Scanner(System in);
 *      3、调用Scanner类的相关方法(next()/nextXxx()),来获取指定类型的变量。
 *      4、释放资源：scanner.close();
 *      注：需要根据相应的方法，来输入指定类型的值。如果输入的数据类型与要求的类型不匹配时，
 *      会报异常，导致程序终止。
 *
 *      获取字符串：scanner.next()
 *      获取浮点型：scanner.nextDouble()
 *      获取布尔型：scanner.nextBoolean()
 *        获取整型：scanner.nextInt()
 *
 */
public class H_KeyboardInput {
    public static void main(String[] args) {
        //②创建扫描对象scanner
        Scanner scanner = new Scanner(System.in);
        //根据提示，调用Scanner的方法，获取不同类型的变量。
        System.out.println("欢迎光临你好我好交友网站！ ");
        System.out.print("请输入你的网名： ");
        String name = scanner.next();
        System.out.print("请输入你的年龄： ");
        int age = scanner.nextInt();
        System.out.print("请输入你的体重： ");
        double weight = scanner.nextDouble();
        System.out.print("你是否单身（true/false)： ");
        boolean isSingle = scanner.nextBoolean();
        System.out.print("请输入你的性别： ");
        char gender = scanner.next().charAt(0);//先按照字符串接收，然后再取字符串的第一个字符（下标为 0）
        System.out.println("你的基本情况如下： ");
        System.out.println(" 网名： " + name + "\n 年龄： " + age + "\n 体重： " + weight +
                "\n 单身： " + isSingle + "\n 性别： " + gender);
    }
}
