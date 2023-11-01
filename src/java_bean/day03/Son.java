package java_bean.day03;

/**
 * @ClassName: Son.java
 * @Author: anpeng
 * @Date: 2023/11/1 9:58
 */
public class Son extends Father{
    //@Override //尝试重写静态方法，加上@Override注解，编译会报错，去掉@Override不报错，但是也不是重写。
    public static void func(){
        System.out.println("Son.func");
    }
}
