package day02_java_oop;

import java_bean.day02.LocalOuter;
import java_bean.day02.MemberOuter;
import java_bean.day02.Runner;

/**
 * 什么是内部类：
 *      将一个类A定义在另一个类B的里面，则里面的类A就称为内部类(InnerClass)，类B则称为外部类(OuterClass)。
 *
 * 为什么要声明内部类：
 *      具体来说，当一个事物A的内部，还有一个部分需要一个完整的结构B进行描述，而这个内部的完整的结构B又只为外部事物A提供服务，
 *      不在其他地方单独使用，那么整个内部的完整结构B最好使用内部类。总的来说，遵循高内聚、低耦合的面向对象开发原则。
 *
 * 内部类的分类：
 *      * 成员内部类
 *          静态成员内部类
 *          非静态成员内部类
 *      * 局部内部类
 *          匿名局部类
 *          非匿名局部类
 *
 * 成员内部类：如果成员内部类中只使用外部类的静态成员，那么通常将内部类声明为静态内部类，否则声明为非静态内部类。
 *      语法格式：
 *          [修饰符] class 外部类{
 *              [其他修饰符] [static] class 内部类{
 *              }
 *          }
 *      使用特征：
 *          成员内部类作为成员的角色：
 *              * 和外部类只能声明为public、default不同， InnerClass还可以声明为private或protected；
 *              * 可以调用外部类的结构。（注意：在静态内部类中只能使用外部类的静态成员）
 *          成员内部类作为类的角色：
 *              * 可以在内部定义属性、方法、构造器等结构
 *              * 可以继承自己想要继承的父类，实现自己想要实现的父接口，和外部类的父类和父接口无关
 *              * 可以声明为abstract类 ，因此可以被其它的内部类继承
 *              * 可以声明为final的，表示不能被继承
 *              * 编译以后生成 OuterClass$InnerClass.class 字节码文件（也适用于局部内部类）
 *
 *      注意事项：
 *          * 外部类访问成员内部类的成员，需要使用“内部类.静态成员”或“内部类对象.成员”的方式访问类成员和实例成员
 *          * 成员内部类可以直接使用外部类的所有成员，包括私有的数据。
 *          * 当想要在外部类的静态成员中使用内部类时，可以考虑将内部类声明为静态的
 *
 *      创建方法：
 *          * 实例化静态成员内部类：
 *              外部类名.静态内部类名 变量 = 外部类名.静态内部类名();
 *              变量.非静态方法();
 *          * 实例化非静态成员内部类：
 *              外部类名 变量1 = new 外部类();
 *              外部类名.非静态内部类名 变量2 = 变量1.new 非静态内部类名();
 *              变量2.非静态方法();
 *              ---> 可以简化为 外部类.非静态成员类 变量 = new 外部类名().new 非静态成员类名();（这里使用了匿名对象）
 *                 变量.非静态方法();
 *
 * 局部内部类
 *      非匿名局部内部类：
 *          语法格式：
 *              [修饰符] class 外部类{
 *                  [修饰符] 返回值类型 方法名(形参列表){
 *                      [final/abstract] class 内部类{
 *                      }
 *                  }
 *              }
 *          注意事项：
 *              * 编译后有自己的独立的字节码文件，只不过在内部类名前面冠以外部类名、 $符号、编号[编号是因为同一个外部类的不同方法中
 *                  可能存在相同名称的局部类。
 *              * 和成员内部类不同的是，它前面不能有权限修饰符等。
 *              * 局部内部类如同局部变量一样，有作用域。即在该方法外，局部类不可见。
 *              * 局部内部类中是否能访问外部类的非静态的成员，取决于所在的方法是否为静态。
 *
 *      匿名内部类：如果子类或实现类是一次性的，那么我们“费尽心机”的给它取名字，就显得多余。那么我们完全可以使用匿名内部类的方式来实现，
 *                避免给类命名的问题。
 *          语法格式：
 *              new 父类([实参列表]){
 *                  重写方法
 *              }
 *              new 父接口(){
 *                  重写方法
 *              }
 *
 *          使用举例：
 *              1、使用匿名内部类的对象直接调用方法
 *              2、通过父类或父接口的变量多态引用匿名内部类的对象
 *              3、匿名内部类的对象作为实参
 *
 *
 * @ClassName: M_InterClass.java
 * @Author: anpeng
 * @Date: 2023/11/3 15:57
 */
public class M_InnerClass {
    public static void main(String[] args) {
        System.out.println("成员内部类--------------------------------");
        //通过创建静态内部类来实例调用其非静态方法
        MemberOuter.StaticMemberInner smi = new MemberOuter.StaticMemberInner();
        smi.inFunc();
        //直接调用静态内部类的静态方法
        MemberOuter.StaticMemberInner.inMethod();

        //创建非静态成员内部类实例的方法1
        MemberOuter.NoStaticMemberInner osmi = new MemberOuter().new NoStaticMemberInner();
        osmi.inFunc();
        //创建非静态成员内部类实例的方法2
        MemberOuter.NoStaticMemberInner osmi1 = new MemberOuter().getNoStaticMemberInner();
        osmi1.inFunc();

        System.out.println("局部内部类--------------------------------");
        //非匿名局部内部类
        LocalOuter.outMethod();//外部类的静态方法中的局部类

        LocalOuter localOuter = new LocalOuter();//外部类的实例方法中的局部类
        localOuter.outTest();

        Runner runner = LocalOuter.getRunner();//外部类的实例方法中的局部类
        runner.run();

        //匿名局部内部类
        //1、使用匿名内部类的对象直接调用方法
        new Runner(){
            @Override
            public void run() {
                System.out.println("1、使用匿名内部类的对象直接调用方法");
            }
        }.run();

        //2、通过父类或父接口的变量多态引用匿名内部类的对象
        Runner runner1 = new Runner() {
            @Override
            public void run() {
                System.out.println("2、通过父类或父接口的变量多态引用匿名内部类的对象");
            }
        };
        runner1.run();

        //3、匿名内部类的对象作为实参
        test(new Runner() {
            @Override
            public void run() {
                System.out.println("3、匿名内部类的对象作为实参");
            }
        });

    }

    public static void test(Runner runner){
        runner.run();
    }
}
