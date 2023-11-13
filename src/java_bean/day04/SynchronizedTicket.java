package java_bean.day04;

/**
 * @ClassName: SynchronizedTicket.java
 * @Author: anpeng
 * @Date: 2023/11/11 15:03
 */
public class SynchronizedTicket {
    private int ticket = 1000;//1000张票便于观察，票数太少的话，一个线程就卖完了

    public void sale(){//也可以直接给这个方法加锁，锁对象是this,也就是SynchronizedTicket的对象，但属于非静态方法加锁了
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "卖 出一张票，票号:" + ticket);
            ticket--;
        } else {
            throw new RuntimeException("没有票了");
        }
    }
}
