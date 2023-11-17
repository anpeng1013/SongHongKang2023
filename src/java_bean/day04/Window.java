package java_bean.day04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Window.java
 * @Author: anpeng
 * @Date: 2023/11/11 21:26
 */
public class Window implements Runnable{
    int ticket = 100;
    //1、创建Lock的实例，必须确保多个线程共享同一个Lock实例
    private final ReentrantLock lock = new ReentrantLock();//开发中最常用的就是这个，可重入锁。

    @Override
    public void run() {
        while (true) {
            try {
                //2、调用lock()，实现需共享代码的锁定
                lock.lock();
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                    ticket--;
                    Thread.yield();//当前线程放弃CPU资源重新调度，方便观察多线程执行情况。
                }else {
                    break;
                }
            }finally {
                //3、调用unlock()，释放共享代码的锁定
                lock.unlock();
            }
        }
    }
}
