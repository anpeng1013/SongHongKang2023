package day01_java_foundation;

/**
 * 这是文档注释，只能放在类或者方法的的外面，对类和方法进行说明。
 *
 * 1.Java中的注释的种类：单行注释、多行注释、文档注释
 * 2.单行注释、多行注释的作用：
 *      ① 对程序中的代码进行解释说明
 *      ② 对程序进行调试
 * 3.单行注释和多行注释的内容不参与编译，多行注释不能嵌套使用。
 * 4.文档注解：
 *  文档注解内容可以被JDK提供的工具javadoc所解析，生成一套以网页文件形式体现的该程序的说明文件。
 *  命令行窗口输入如下解析命令：
 *      javadoc -d B_Comment_doc -encoding utf-8 -author -version b_Comment.java
 * 注意：
 *      * 单行注释和多行注释是给程序员看的
 *      * 而注解是可以被编译器或其他程序读取的。程序还可以根据注解的不同，做出相应的处理。
 * @author anpeng
 * @version 1.0
 */
public class B_Comment {
    /**
     * 对main方法进行注释说明
     * @param args args名称可以修改。
     */
    public static void main(String[] args) {
        System.out.println("anpeng love huli forever");
        //这是单行注释
        System.out.println("this is a comment file");
        /*
        这是多行注释，
        第一个斜杠后面只有一颗星。
         */
        System.out.println("123");
    }
}