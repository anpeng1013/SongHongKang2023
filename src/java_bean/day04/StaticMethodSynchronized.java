package java_bean.day04;

/**
 * @ClassName: StaticMethodSynchronized.java
 * @Author: anpeng
 * @Date: 2023/11/11 11:34
 */
public class StaticMethodSynchronized extends Thread{
    private static int ticket = 300;

    public StaticMethodSynchronized(String s) {
        super(s);
    }

    @Override
    public void run() {//直接锁这里肯定不行，会导致只有一个窗口卖票
        while (ticket > 0){
            saleOneTicket();
        }
    }

    private synchronized static void saleOneTicket() {
        //锁对象是 StaticMethodSynchronized 类的 Class 对象，而一个类的 Class 对象在内存中肯定只有一个。
        if(ticket > 0){//不加条件，相当于条件判断没有进入锁管控，线程安全问题就没有解决
            System.out.println(Thread.currentThread().getName() + "卖出一张票，票号：" + ticket);
            ticket--;
        }
    }
}
