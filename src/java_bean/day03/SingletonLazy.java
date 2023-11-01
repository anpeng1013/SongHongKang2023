package java_bean.day03;

/**
 * @ClassName: SingletonLazy.java
 * @Author: anpeng
 * @Date: 2023/11/1 14:50
 */
public class SingletonLazy {
    //1.私有化构造器
    private SingletonLazy(){}

    //2.内部提供一个当前类的静态实例。
    private static SingletonLazy singletonLazy;

    //3.提供公共的静态的方法，返回当前类的单例对象，先判断该单例对象是否为空，如果为空则创建一个；若不为空则返回该单例对象。
    public static SingletonLazy getInstance(){
        if(singletonLazy == null){
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }
}
