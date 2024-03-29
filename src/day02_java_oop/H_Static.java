package day02_java_oop;

import java_bean.day02.Father;
import java_bean.day02.Son;

/**
 * static关键字：
 *      以static修饰的成员变量、成员方法被称为类属性(或静态变量)和类方法(或静态方法)，统称类(静态)成员。可以被类的所有实例所共享。
 *
 * 设计思想：
 *      某些特有属性在该类的所有实例中永远一致时，可以用static修饰该属性。
 *      某些方法与类的对象无关，通过类名直接调用更方便时，可以用static修改该方法。
 *
 * 使用范围：
 *      在java类中，可用static修饰属性、方法、代码块、内部类。
 *
 * 静态特点：
 *      * 随着类的加载而加载
 *      * 优先于对象存在
 *      * 修饰的成员，被所有对象所共享
 *      * 访问权限允许时，可不创建对象，直接被类调用。
 *
 * 静态变量：
 *      * 静态变量的默认规则和实例变量一样
 *      * 静态变量的值存储在方法区（类信息、常量、静态变量），实例变量的值存储在堆区（存储new出来的对象）。
 *      * 静态变量的get/set方法也是静态的，当局部变量和静态变量重名时，使用“类名.静态变量”进行区分。
 *
 * 静态方法：
 *      * 在静态方法内部只能访问类的static修饰的属性或方法，不能访问非static结构。
 *      * 静态方法可以被子类继承，但不能被重写。[注意！接口中的静态方法既不能被继承，也不能被重写]
 *      * 静态方法可以被继承，但不能被重写。因为重写是为了达到对象方法的多态性，但静态方法的调用只看编译时类型，故当父类引用指向子类对象时，
 *          此刻调用子父类同名的静态方法，实际调用的是父类静态方法，没有实现对象成员方法的多态。
 *      * 因为不需要实例就可以访问static方法，因此static方法内部不能有this和super，如果有重名问题，使用类名进行区分。
 *
 * 总结：
 *      静态只能调用静态；而非静态即可以调用非静态，也可以调用静态。
 *
 * @ClassName: H_Static.java
 * @Author: anpeng
 * @Date: 2023/10/31 21:05
 */
@SuppressWarnings("all")
public class H_Static {
    public static void main(String[] args) {
        Father.method();
        Son.method();//子类继承静态方法。

        Father father = new Son();
        father.func();//执行的是编译时类型Father中的func
        System.out.println(father);//运行时类型还是Son--java_bean.day03.Son@41629346

    }
}
