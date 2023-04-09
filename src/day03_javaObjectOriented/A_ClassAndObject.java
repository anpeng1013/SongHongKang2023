package day03_javaObjectOriented;

import javaBean.day03.Person;

/**
 * @ClassName: A_ClassAndObject.java
 * @Author: anpeng
 * @Date: 2023/4/9 12:44
 *
 *  类(class)：具有相同特征的事物的抽象描述，是抽象的、概念上的定义。
 *  对象(object)：实际存在的该类事物的每个个体，是具体的，因而也称为实例(instance)。
 *
 *  类的定义：
 *      [修饰符] class 类名{
 *          属性声明;
 *          方法声明;
 *      }
 *
 *  创建对象：
 *      类名 对象名 = new 类名();
 *      或   new 类名();//也称为匿名对象
 *
 *  对象调用：
 *      对象名.属性
 *      对象名.方法
 *
 *  匿名对象：
 *      可以不定义对象的句柄，而直接使用这个对象的方法，这样的对象叫匿名对象(anonymous object)。
 *      如：new Person().speak();
 *      使用情况：如果一个对象只需要进行一次方法调用，经常将匿名对象作为实参传递。
 *
 *  成员变量 VS 局部变量：
 *      在java中没有全局变量这一说法，所有变量都定义在类属性或方法中。
 *      变量：
 *          局部变量：在参数列表、方法内部、代码块内部定义的变量。 --> 存放在栈内存中，没有默认初始值。
 *
 *          成员变量：在类的属性中定义的变量。 --> 存放在堆内存中，有默认初始值。
 *                   类变量：以static修饰。
 *                   实例变量：不以static修饰
 *
 *
 */
public class A_ClassAndObject {
    public static void main(String[] args) {
        Person anpeng = new Person(26, "anpeng");
        anpeng.speak();
        //使用匿名对象
        System.out.println("the age of huli = " + new Person(24, "huli").getAge());
    }
}
