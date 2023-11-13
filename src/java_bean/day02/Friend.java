package java_bean.day02;

/**
 * @ClassName: Friend.java
 * @Author: anpeng
 * @Date: 2023/11/2 17:10
 */
public interface Friend {
    int constant = 1124;

    void date();

    default void greeting() {
        System.out.println("握手");
    }
}
