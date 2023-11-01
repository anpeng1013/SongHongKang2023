package day03_java_object_oriented;

import java_bean.day03.SingletonHungry;
import java_bean.day03.SingletonLazy;

/**
 * 设计模式：
 *      是在大量的实践中总结和理论化之后优选的代码结构、编程风格、以及解决问题的思考方式。设计模式免去我们自己再思考和摸索。就像是经典的棋谱，
 *      不同的棋局，我们用不同的棋谱。经典的设计模式共有23种。
 *      * 创建型模式 5+1
 *          单例、原型、创建、抽象工厂、工厂方法 + 简单工厂（不属于经典模式，是工厂方法模式的简化版）
 *      * 结构型模式 7
 *          外观、适配器、代理、装饰、桥接、组合、享元
 *      * 行为型模式 11
 *          模板方法、观察者、状态、策略、职责链、命令、访问者、调停者、备忘录、迭代器、解释器
 *
 * 单例模式：
 *      采取一定的方法保证在整个的软件系统中，某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法。
 *
 *      实现思路：
 *          让类在一个虚拟机中只能产生一个对象，首先必须将类的构造器的访问权限设置为private，这样，就不能用new操作符在类的外部产生类的对象了，
 *          但在类内部仍可以产生该类的对象。因为在类的外部一开始还无法得到类的对象， 只能调用该类的某个静态方法以返回类内部创建的对象，静态方法
 *          只能访问类中的静态成员变量，所以，指向类内部产生的该类对象的变量也必须定义成静态的。
 *
 *      实现方式：
 *          饿汉式：立即加载，即在类加载进JVM时就已经将单例对象创建完毕。
 *              public class SingletonHungry {
 *                  //1.私有化构造器
 *                  private SingletonHungry(){
 *
 *                  }
 *
 *                  //2.内部提供一个当前类的静态实例，并初始化。
 *                  private static SingletonHungry singletonHungry = new SingletonHungry();
 *
 *                  //3.提供公共的静态的方法，返回当前类的唯一实例对象
 *                  public static SingletonHungry getInstance(){
 *                      return singletonHungry;
 *                  }
 *              }
 *              优点：实现更简单；没有多线程安全问题。
 *              缺点：当类被加载的时候，会初始化static实例，静态变量被创建并分配内存空间，从这以后，这个static实例便一直占着这块内存，
 *                   直到类被卸载时，静态实例变量才被摧毁，并释放所占用的内存。因此在某些特定条件下回耗费内存。
 *
 *          懒汉式：延迟加载，即在调用获取静态单例方法时才被创建。
 *              public class SingletonLazy {
 *                  //1.私有化构造器
 *                  private SingletonLazy(){
 *
 *                  }
 *
 *                  //2.内部提供一个当前类的静态实例，并初始化。
 *                  private static SingletonLazy singletonLazy = new SingletonLazy();
 *
 *                  //3.提供公共的静态的方法，返回当前类的单例对象，先判断该单例对象是否为空，如果为空则创建一个；若不为空则返回该单例对象。
 *                  public static SingletonLazy getInstance(){
 *                      if(singletonLazy == null){
 *                          singletonLazy = new SingletonLazy();
 *                      }
 *                      return singletonLazy;
 *                  }
 *              }
 *              优点：实现比较简单；当类被加载的时候，static实例未被创建并分配内存空间，当静态方法第一次调用时，初始化实例变量，并分配内存，
 *                   因此在某些特定条件下回节约空间。
 *              缺点：在多线程环境中，这种实现方法是完全错误的，线程不安全，根本不能保证单例的唯一性。
 *
 *
 * @ClassName: I_Singleton.java
 * @Author: anpeng
 * @Date: 2023/11/1 11:48
 */
public class I_Singleton {
    public static void main(String[] args) {
        //饿汉式
        SingletonHungry singletonHungry = SingletonHungry.getInstance();
        SingletonHungry singletonHungry1 = SingletonHungry.getInstance();
        System.out.println(singletonHungry.equals(singletonHungry1));

        //懒汉式
        SingletonLazy singletonLazy = SingletonLazy.getInstance();
        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        System.out.println(singletonLazy.equals(singletonLazy1));
    }
}
