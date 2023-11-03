package java_bean.day03;

/**
 * @ClassName: A.java
 * @Author: anpeng
 * @Date: 2023/11/2 15:35
 */
public interface A {
    void showA();
    static void staticMethod(){
        System.out.println("接口的实现类不能调用接口的静态方法");
    }
    default void defaultMethod(){
        System.out.println("default method of A");
    }
}
