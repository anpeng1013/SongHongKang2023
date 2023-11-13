package java_bean.day04;

/**
 * @ClassName: LazyOne.java
 * @Author: anpeng
 * @Date: 2023/11/11 16:28
 */
public class LazyOne {
    private static LazyOne instance;

    private LazyOne(){}

    //原始写法--具有线程安全问题
    public static LazyOne getInstance(){
        if(instance==null){
            Thread.yield();//重新调度，可以暴露多线程下的安全问题
            instance = new LazyOne();
        }
        return instance;
    }

    //两种方式解决线程安全问题
    //方式1--静态方法加锁
    public static synchronized LazyOne getInstance1(){
        if(instance==null){
            instance = new LazyOne();
        }
        return instance;
    }
    //方式2--代码块加锁
    public static LazyOne getInstance2(){
        synchronized(LazyOne.class){
            if(instance==null){
                instance = new LazyOne();
            }
            return instance;
        }
    }

}
