package java_bean.day02;

/**
 * @ClassName: Computer.java
 * @Author: anpeng
 * @Date: 2023/11/2 14:51
 */
public class Computer {
    public static void show(USB usb){
        usb.start();
        System.out.println("============USB设备工作中===========");
        usb.stop();
    }
}
