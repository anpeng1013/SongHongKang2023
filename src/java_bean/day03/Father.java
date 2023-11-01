package java_bean.day03;

/**
 * @ClassName: Father.java
 * @Author: anpeng
 * @Date: 2023/11/1 9:57
 */
public class Father {
    public final void notOverride(){//final修饰的方法不可被重写。
        System.out.println("not override");
    }

    public static void method(){
        System.out.println("Father method");
    }

    public static void func(){
        System.out.println("Father.func");
    }
}
