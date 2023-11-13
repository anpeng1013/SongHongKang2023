package java_bean.day02;

/**
 * @ClassName: TypeCConverter.java
 * @Author: anpeng
 * @Date: 2023/11/2 16:01
 */
public class TypeCConverter implements UsbC{//UsbC继承了多个父接口，它的实现类需要重写所有抽象方法
    @Override
    public void reverse() {
        System.out.println("正反面都支持");
    }

    @Override
    public void charge(){
        System.out.println("可充电");
    }

    @Override
    public void in() {//USB3和Chargeable接口中的方法签名相同的抽象方法只需要实现一次
        System.out.println("接收数据");
    }

    @Override
    public void out() {//USB3和Chargeable接口中的方法签名相同的抽象方法只需要实现一次
        System.out.println("输出数据");
    }

    @Override
    public void start(){//实现类中重写父接口的默认方法要去掉default,但不能去掉public
        System.out.println("TypeCConverter开始传输");
    }

    @Override
    public void stop(){//实现类中重写父接口的默认方法要去掉default,但不能去掉public
        System.out.println("TypeCConverter停止传输");
    }
}
