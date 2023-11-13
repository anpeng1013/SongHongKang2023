package java_bean.day04;

/**
 * @ClassName: StaticTicket.java
 * @Author: anpeng
 * @Date: 2023/11/11 10:14
 */
public class StaticTicket extends Thread{
    private static int ticket = 10;

    public StaticTicket(String s) {
        super(s);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (ticket > 0) {
            try {
                Thread.sleep(10);//加入这个，使得问题暴露更明显
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(getName() + "卖出一张票，票号:" + ticket);
            ticket--;
        }
    }
}
