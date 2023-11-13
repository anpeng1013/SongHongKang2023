package java_bean.day02;

/**
 * @ClassName: Father.java
 * @Author: anpeng
 * @Date: 2023/11/1 9:57
 */
public class Father {
    public int constant = 1013;

    public final void notOverride(){//final修饰的方法不可被重写。
        System.out.println("not override");
    }

    public void date(){//约会
        System.out.println("爸爸约吃饭");
    }
    public void greeting(){
        System.out.println("拥抱");

    }
    public static void method(){
        System.out.println("Father method");
    }

    public static void func(){
        System.out.println("Father.func");
    }
}
