package java_bean.day04;

/**
 * @ClassName: TicketWindow.java
 * @Author: anpeng
 * @Date: 2023/11/11 10:05
 */
public class DifferentInstanceTicket extends Thread{
    private int ticket = 10;//不同Thread子类实例的成员变量不能共享。

    public DifferentInstanceTicket(String str){
        super(str);
    }

    @Override
    public void run() {
        while (ticket > 0){
            System.out.println(getName() + "卖出一张票，票号：" + ticket);
            ticket -= 1;
        }
    }
}
