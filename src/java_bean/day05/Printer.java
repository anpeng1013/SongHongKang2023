package java_bean.day05;

/**
 * @ClassName: Printer.java
 * @Author: anpeng
 * @Date: 2023/11/12 10:01
 */
public class Printer implements Runnable {
    int i = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {//1.先获取锁
                notify();//2.唤醒协作线程，让协作线程能够在当前线程结束后能去争取锁
                if (i <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + i++);
                } else {
                    break;//要先唤醒，不然打印完后在这里直接退出while
                }
                //notify();//不能在这里唤醒，因为打印结束后(i>10)，break直接跳出了while循环，无法执行这里的唤醒操作了
                try {
                    wait();//3.当前线程完成特定动作，自陷等待，不再参与锁和CPU的竞争，等待协作线程获得锁后唤醒。
                    // 注意：当前线程在被唤醒的瞬间，锁还在协作线程手中，故不能立即运行，得等到协作线程完成特定动作自陷后放弃锁和CPU，
                    //当前线程才能执行。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
