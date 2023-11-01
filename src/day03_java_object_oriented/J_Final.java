package day03_java_object_oriented;

import java_bean.day03.Child;

/**
 *
 * final关键字：
 *      final的意义：最终的，不可更改的。
 *
 *      final的使用：
 *          final修饰类：表示这个类不能被继承，没有子类。提高安全性。例如，String类、System类、StringBuffer类及自定义的太监Eunuch类。
 *          final修饰方法：表示这个方法不能被重写。见Child类
 *          final修饰变量：表示该变量一旦赋值，它的值就不能被更改，即常量。常量名建议使用大写字母。见Child类。
 *
 * @ClassName: J_Final.java
 * @Author: anpeng
 * @Date: 2023/11/1 15:56
 */
public class J_Final {
    public static void main(String[] args) {
        Child child = new Child("男");
        System.out.println(child.getGENDER()); //GENDER没有set方法，因为final修饰的常量不能被修改。
    }
}
