package java_bean.day04;

/**
 * @ClassName: Window.java
 * @Author: anpeng
 * @Date: 2023/11/11 9:57
 */
public class LocalTicket extends Thread{
    public LocalTicket(String str) {
        super(str);
    }

    @Override
    public void run() {
        int tickets = 10;
        while (tickets > 0){
            System.out.println(getName() + "卖出一张票，票号：" + tickets);
            tickets -= 1;
        }
    }
}
