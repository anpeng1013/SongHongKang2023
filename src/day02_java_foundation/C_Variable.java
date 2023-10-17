package day02_java_foundation;

/**
 * 变量的概念：
 *   内存中的一个存储区域，该区域的数据可以在同一类型范围内不断变化。
 *   变量的构成包含三个要素： 数据类型、 变量名、 存储的值。
 *   Java中变量声明的格式： 数据类型 变量名 = 变量值。
 * 变量的作用：用于在内存中保存数据
 * 注意事项：
 *   1.Java 中每个变量必须先声明，后使用。
 *   2.使用变量名来访问这块区域的数据。
 *   3.变量的作用域：其定义所在的一对{ }内。
 *   4.变量只有在其作用域内才有效。出了作用域，变量不可以再被调用。
 *   5.同一个作用域内，不能定义重名的变量。
 *
 */
public class C_Variable {
    public static void main(String[] args) {
        //1.声明变量
        String name;
        //2.赋值变量
        name = "anpeng";
        //3.使用变量
        System.out.println("my name is " + name);
        System.out.println("this is variable description document.");
        //4.连续打印多个变量
        printMultiVariable();
    }

    static void printMultiVariable() {
        String str = "anpeng love huli.";
        int date = 20181124;
        char c = 'a';
        //java不能像Python的print(name,date,c)一样连续打印变量。
        //但可以通过字符串格式化和字符串拼接实现

        //通过“+”对字符串进行拼接
        System.out.println(str + ", " + date);
        //格式化字符串，打印多个变量
        System.out.printf("%s, %d, %c\n", str, date, c);
    }
}
