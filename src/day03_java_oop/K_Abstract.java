package day03_java_oop;

import java_bean.day03.BankTemplateMethod;
import java_bean.day03.DrawMoney;
import java_bean.day03.ManageMoney;

/**
 * abstract关键字：
 *      由来：我们声明一些几何图形类：圆、矩形、三角形类等，发现这些类都有共同特征：求面积、求周长。那么这些共同特征应该抽取到一个
 *      共同的父类：几何图形类中。但是这些方法在父类中又无法给出具体的实现，而是应该交给子类各自具体实现。那么父类在声明这些方法时，
 *      就只有方法签名，没有方法体，我们把没有方法体的方法称为抽象方法。
 *
 *      抽象方法：被 abstract 修饰的没有方法体的方法。
 *      抽象类：被 abstract 修饰的包含抽象方法的类。java语法规定：包含抽象方法的类必须是抽象类。
 *          * 抽象类不能创建实例对象，只能创建非抽象子类的实例。
 *              理解：假设创建了抽象类的对象，并调用其抽象方法，但没有具体的方法体，没有意义。
 *          * 抽象类是用来继承的，抽象类的子类必须重写父类的全部抽象方法，并提供方法体。若没有重写全部的抽象方法，子类仍为抽象类。
 *          * 抽象类中，也有构造方法，是供子类创建对象时，初始化父类成员变量使用的。
 *              理解：子类的构造方法中，有默认的super()或手动的super(实参列表)，需要访问父类的构造方法。
 *          * 抽象类中，不一定包含抽象方法，但是有抽象方法的类必定是抽象类。
 *              理解：未包含抽象方法的抽象类，目的就是不想让调用者创建该类对象，通常用于某些特殊的类结构设计。
 *
 *      注意：
 *          * 不能用 abstract 修饰变量、代码块、构造器。
 *              理解：因为变量、代码块、构造器这些不能被重写。
 *          * 不能用 abstract 修饰私有方法、静态方法、 final的方法、 final 的类。
 *              理解：因为abstract修饰的方法需要被子类重写，但私有、静态、final方法不能被重写；
 *                   abstract修饰的类是用来的继承的，而final修饰的类的不能被继承。
 *
 *      应用：
 *          模板方法设计模式: TemplateMethod
 *              抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类在抽象类的基础上进行扩展、改造，
 *              但子类总体上会保留抽象类的行为方式。换句话说，在软件开发中实现一个算法时，整体步骤很固定、通用，这些步骤已经在父类中写好了。
 *              但是某些部分易变，易变部分可以抽象出来，供不同子类实现。这就是一种模板方法模式
 *
 *              模板方法设计模式是编程中经常用到的设计模式。各个框架、类库中都有他的影子，比如常见的有：
 *                  * 数据库访问的封装。
 *                  * Junit单元测试
 *                  * javaWeb的servlet中关于doGet/doPost方法调用
 *                  * Hibernate中模板程序
 *                  * Spring 中 JDBCTemplate、 HibernateTemplate 等
 *
 *
 * @ClassName: K_Abstract.java
 * @Author: anpeng
 * @Date: 2023/11/1 16:20
 */
public class K_Abstract {
    public static void main(String[] args) {
        //模板方法
        BankTemplateMethod btm = new DrawMoney();
        btm.process();

        BankTemplateMethod btm1 = new ManageMoney();
        btm1.process();
    }
}
