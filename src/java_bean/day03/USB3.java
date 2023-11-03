package java_bean.day03;

/**
 * @ClassName: USB3_1.java
 * @Author: anpeng
 * @Date: 2023/11/1 21:41
 */
public interface USB3 {
    //公共的静态常量,可以省略public static final
    long MAX_SPEED = 500*1024*1024; // 500MB/s

    //公共的抽象方法,可以省略public abstract
    void in();
    void out();

    //公共的默认方法，可以省略public，不能省略default
    default void start(){
        System.out.println("USB3开始传输");
    }
    default void stop(){
        System.out.println("USB3结束传输");
    }

    //公共的静态方法，可省略public，不能省略static
    static void show(){
        System.out.println("USB3可以同步全速地进行读写操作");
    }

    default void defaultMethod(){
        System.out.println("default method of USB3");
    }
}
