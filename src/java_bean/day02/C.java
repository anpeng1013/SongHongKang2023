package java_bean.day02;

/**
 * @ClassName: C.java
 * @Author: anpeng
 * @Date: 2023/11/2 15:37
 */
public class C implements A,B{
    @Override
    public void showA() {
        System.out.println("showA");
    }

    @Override
    public void showB() {
        System.out.println("showB");
    }

    @Override
    public void defaultMethod() {//子类实现的多个父接口中有多个同名的默认方法时，实现类必须重写该同名的默认方法。
        A.super.defaultMethod();//可以用接口名.super.默认方法名的方式在子类重写中继续执行父接口的默认方法，也可以不用。
        B.super.defaultMethod();
        System.out.println("default method of C");
    }
}
