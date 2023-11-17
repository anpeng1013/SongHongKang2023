package java_bean.day04;

/**
 * @ClassName: SameInstanceTicket.java
 * @Author: anpeng
 * @Date: 2023/11/11 10:29
 */
public class SameInstanceTicket implements Runnable{
    private int ticket = 10;//用同一个Runnable实现类对象创建多线程时，实现类对象的实例变量被所有线程所共享

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (ticket > 0) {
            try {
                Thread.sleep(10);//加入这个，使得问题暴露更明显
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
            ticket--;
        }
    }
}
