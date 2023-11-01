package day03_java_object_oriented;

import java_bean.day03.Person;

/**
 * @ClassName: D_Constructor.java
 * @Author: anpeng
 * @Date: 2023/4/9 21:38
 *
 * 构造器：
 *      格式：
 *          [修饰符] class 类名{
 *              [修饰符] 类名(){
 *              // 实例初始化代码
 *              }
 *              [修饰符] 类名(参数列表){
 *              // 实例初始化代码
 *              }
 *          }
 *      说明：
 *          1、构造器名必须与它所在的类名必须相同。
 *          2、它没有返回值，所以不需要返回值类型，也不需要void。
 *          3、没有显式的声明类中的构造器时，系统会默认提供一个无参的构造器并且该构造器的修饰符默认与类的修饰符相同
 *          4、构造器的修饰符只能是权限修饰符，不能被其他任何修饰。比如，不能被static、
 *              final、synchronized、abstract、native修饰，不能有return语句返回值。
 *          5、可以调用静态的属性和方法。
 */
public class B_Constructor {
    public static void main(String[] args) {
        //使用package javaBean.day03包中的Person类的无参构造器
        Person person = new Person();
        person.setAge(26);
        person.setName("anpeng");
        System.out.println(" name = " + person.getName() + " age is " + person.getAge());
    }
}
