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
 *  成员属性赋值过程：
 *      1、何处赋值
 *          ①默认初始化。比如，private int age;  age默认为0
 *          ②显示初始化。比如，private int age=18;
 *          ③构造器初始化。 比如，new Person(26,"anpeng");
 *          ④通过"对象.属性"或"对象.SetXxx()"的方式，给属性赋值。
*       2、先后顺序
 *          ① --> ② --> ③ --> ④
 *      3、说明：上述中的①、②、③在对象创建过程中只执行一次，④是在对象创建后执行的，可以根据需求多次执行。
 *
 *
 *  javaBean,是指符合如下标准的java类：
 *      ① JavaBean必须是公共的
 *      ② JavaBean有一个不带参数的构造函数，如果public类的构造函数包含参数的话，那这个类不能做为JavaBean
 *      ③ JavaBean属性默认是私有的，通过getProperty获取属性，通过setProperty设置属性
 *
 *  this关键字：
 *      在实例方法(非static)中使用，表示调用该方法的对象；this在构造器内部使用，表示该构造器正在初始化的对象。
 *      可调用的结构：成员变量、方法和构造器。
 *      this使用时机：
 *          在实例方法或构造器中使用当前对象的成员时:
 *              在实例方法或构造器中，如果使用当前类的成员变量或成员方法可以在其前面添加this，
 *              增强程序的可读性。不过，通常我们都习惯省略this。但是，当形参与成员变量同名时，如果在方法内或构造器内需要使用成员变量，
 *              必须添加this来表明该变量是类的成员变量。即：我们可以用this来区分成员变量和局部变量。
 *          在同一个类中构造器互相调用时：
 *              this 可以作为一个类中构造器相互调用的特殊格式。
 *              this():调用本类的无参构造器。
 *              this(参数列表):调用本类的有参构造器。
 */
public class A_ClassAndObject {
    public static void main(String[] args) {
        Person anpeng = new Person();
        anpeng.setName("anpeng");
        anpeng.setAge(26);
        anpeng.speak();
        //使用匿名对象
        System.out.println(new Person().getAge()); //int类型默认是0
    }
}
