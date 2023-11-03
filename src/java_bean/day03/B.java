package java_bean.day03;

/**
 * @ClassName: B.java
 * @Author: anpeng
 * @Date: 2023/11/2 15:36
 */
public interface B {
    void showB();

    default void defaultMethod(){
        System.out.println("default method of B");
    }
}
