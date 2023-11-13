package java_bean.day02;

/**
 * @ClassName: Son.java
 * @Author: anpeng
 * @Date: 2023/11/1 9:58
 */
public class Son extends Father implements Friend{
    //@Override //尝试重写静态方法，加上@Override注解，编译会报错，去掉@Override不报错，但是也不是重写。
    public static void func(){
        System.out.println("Son.func");
    }

    //可以不用再重写Friend接口中的date抽象方法了，继承自父类的同名方法可以看作是对接口中抽象方法的实现。

}
