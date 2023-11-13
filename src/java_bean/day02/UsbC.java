package java_bean.day02;

/**
 * @ClassName: UsbC.java
 * @Author: anpeng
 * @Date: 2023/11/2 15:57
 */
public interface UsbC extends Chargeable, USB3{//子接口继承了多个父接口
    void reverse();

    @Override
    default void start(){//子接口中重写默认方法时，可以去掉public,但不能去掉default。
        System.out.println("UsbC开始传输");
    }

    @Override
    default void stop() {//子接口中重写默认方法时，可以去掉public,但不能去掉default。
        System.out.println("UsbC停止传输");
    }

    @Override
    default void defaultMethod() {//子接口继承的多个父接口中包含方法签名相同的默认方法时，子接口必须重写重名的默认方法。default必须保留。
        Chargeable.super.defaultMethod();//可以用接口名.super.默认方法名的方式在子接口重写中继续执行父接口的默认方法，也可以不用。
        USB3.super.defaultMethod();
        System.out.println("default method of UsbC");
    }
}
