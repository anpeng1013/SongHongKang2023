package java_bean.day02;

/**
 * @ClassName: Flash.java
 * @Author: anpeng
 * @Date: 2023/11/2 14:54
 */
public class Flash implements USB{
    @Override
    public void start() {//重写（实现）USB接口中的start抽象方法
        System.out.println("U盘开始工作");
    }

    @Override
    public void stop() {//重写（实现）USB接口中的stop抽象方法
        System.out.println("U盘停止工作");
    }
}
