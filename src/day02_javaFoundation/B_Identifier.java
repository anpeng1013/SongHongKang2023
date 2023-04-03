package day02_javaFoundation;

/**
 * 标识符：Java 中变量、方法、类等要素命名时使用的字符序列，称为标识符。
 * 命名规则：
 *      1、由26个英文字母大小写，0-9，_或 $ 组成。
 *      2、数字不可以开头。
 *      3、不可以使用关键字和保留字，但能包含关键字和保留字。
 *      4、Java 中严格区分大小写，长度无限制，但不能包含空格。
 *
 *  命令规范：
 *      1、包名：多单词组成时所有字母都小写。例如：java.lang、 com.anpeng.bean
 *      2、类名、接口名：多单词组成时，所有单词的首字母大写。例如：HelloWorld、StringBuilder
 *      3、变量名、方法名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单
 *                     词首字母大写。例如：name、bookName、binarySearch、getName
 *      4、常量名：所有字母都大写。多单词时每个单词用下划线连接。例如：MAX_VALUE、PI、DEFAULT_CAPACITY
 *
 *  标识符，为了阅读方便，起名时应该有意义，做到“见名知意”。
 */
public class B_Identifier {
    public static void main(String[] args) {
        System.out.println("this is a identifier description document.");
    }
}
