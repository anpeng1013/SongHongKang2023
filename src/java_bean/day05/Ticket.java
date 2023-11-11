package java_bean.day05;

/**
 * @ClassName: Ticket.java
 * @Author: anpeng
 * @Date: 2023/11/11 10:43
 */
public class Ticket {
    //1、编写资源类
    private int ticket = 10;
    public void sale() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);//加入这个，使得问题暴露的更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖 出一张票，票号:" + ticket);
            ticket--;
        } else {
            throw new RuntimeException("没有票了");
        }
    }
}
