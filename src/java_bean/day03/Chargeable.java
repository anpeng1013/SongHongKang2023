package java_bean.day03;

/**
 * @ClassName: Chargeable.java
 * @Author: anpeng
 * @Date: 2023/11/2 15:41
 */
public interface Chargeable {//父接口
    void charge();
    void in();
    void out();

    default void defaultMethod(){
        System.out.println("default method of Chargeable");
    }
}
