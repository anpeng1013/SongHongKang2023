package java_bean.day04;

/**
 * @ClassName: NoStaticMethodSynchronized.java
 * @Author: anpeng
 * @Date: 2023/11/11 14:39
 */
public class NoStaticMethodSynchronized implements Runnable{
    private int ticket = 300;

    @Override
    public void run() {//直接锁这里肯定不行，会导致，只有一个窗口卖票
        while (ticket > 0){
            saleOneTicket();
        }
    }

    private synchronized void saleOneTicket() {
        //锁对象是this，这里就是NoStaticMethodSynchronized对象，因为后面3个线程使用同一个NoStaticMethodSynchronized对象创建线程
        if(ticket > 0){//不加条件，相当于条件判断没有进入锁管控，线程安全问题就没有解决，会出现负票数问题
            System.out.println(Thread.currentThread().getName() + "卖出一张票，票号：" + ticket);
            ticket--;
        }
    }
}
