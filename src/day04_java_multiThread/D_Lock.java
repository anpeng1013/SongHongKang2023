package day04_java_multiThread;

import java_bean.day04.Window;

/**
 * JDK1.5新特性：Lock(锁)
 *      * JDK1.5的新增功能，保证线程的安全。与采用 synchronized 相比，Lock可提供多种锁方案，更灵活、更强大。 Lock通过显式定义同步锁对象
 *          来实现同步。同步锁使用Lock对象充当。
 *      * java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具。锁提供了对共享资源的独占访问，每次只能有一个线程
 *          对Lock对象加锁，线程开始访问共享资源之前应先获得 Lock 对象。
 *      * 在实现线程安全的控制中，比较常用的是 ReentrantLock[可重入锁]，可以显式加锁、释放锁。
 *          - 可重入锁 ReentrantLock 类实现了 Lock 接口，它拥有与 synchronized 相同的并发性和内存语义，但是添加了类似锁投票、
 *              定时锁等候和可中断锁等候的一些特性。此外，它还提供了在激烈争用情况下更佳的性能。
 *
 * Lock锁也称为同步锁，加锁与释放锁方法如下：
 *      - public void lock();加同步锁
 *      - public void unlock(); 释放同步锁
 *
 *      代码结构如下：
 *          class A{
 *              //1. 创建 Lock 的实例，必须确保多个线程共享同一个 Lock 实例
 *              private final ReentrantLock lock = new ReentrantLock();
 *              public void m(){
 *                  //2. 调动 lock()，实现需共享的代码的锁定
 *                  lock.lock();
 *                  try{
 *                      //保证线程安全的代码;
 *                  }
 *                  finally{
 *                  //3. 调用 unlock()，释放共享代码的锁定
 *                      lock.unlock();
 *                  }
 *              }
 *          }
 *          注意：如果同步代码块有异常，需要将unlock()写入finally语句块中
 *
 * synchronized 与 Lock 的对比：
 *      1. Lock是显式锁（手动开启和关闭锁，别忘记关闭锁）， synchronized是隐式锁，出了作用域、遇到异常等自动解锁。
 *      2. Lock只有代码块锁， synchronized有代码块锁和方法锁。
 *      3. 使用Lock锁， JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类），更体现面向对象。
 *      4. （了解） Lock 锁可以对读不加锁，对写加锁， synchronized 不可以。
 *      5. （了解） Lock 锁可以有多种获取锁的方式，可以从 sleep 的线程中抢到锁，synchronized 不可以
 *
 * 开发中建议处理线程安全问题的优先使用顺序为：
 *      Lock --> 同步(synchronized)代码块 --> 同步(synchronized)方法
 *
 * @ClassName: DLockTest.java
 * @Author: anpeng
 * @Date: 2023/11/11 17:51
 */
public class D_Lock {
    public static void main(String[] args) {
        Window window = new Window();
        Thread t1 = new Thread(window,"窗口1");
        Thread t2 = new Thread(window,"窗口2");
        Thread t3 = new Thread(window,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
