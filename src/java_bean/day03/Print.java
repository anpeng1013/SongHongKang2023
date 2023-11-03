package java_bean.day03;

/**
 * @ClassName: Print.java
 * @Author: anpeng
 * @Date: 2023/11/2 14:57
 */
public class Print implements USB{
    @Override
    public void start() {//重写（实现）USB接口中的start抽象方法
        System.out.println("打印机开始工作");
    }

    @Override
    public void stop() {//重写（实现）USB接口中的stop抽象方法
        System.out.println("打印机停止工作");
    }
}
