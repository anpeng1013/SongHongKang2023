package java_bean.day03;

/**
 * @ClassName: SingletonHungry.java
 * @Author: anpeng
 * @Date: 2023/11/1 12:08
 */
public class SingletonHungry {
    //1.私有化构造器
    private SingletonHungry(){

    }
    //2.内部提供一个当前类的静态实例，并初始化。
    private static final SingletonHungry singletonHungry = new SingletonHungry();

    //3.提供公共的静态的方法，返回当前类的唯一实例对象
    public static SingletonHungry getInstance(){
        return singletonHungry;
    }
}
