package day05_java_multiThread;

import java_bean.day05.*;
import org.junit.Test;

/**
 * 线程安全
 *      当我们使用多个线程访问同一资源（可以是同一个变量、同一个文件、同一条记录等）的时候，若多个线程只有读操作，那么不会发生线程安全问题。
 *      但是如果多个线程中对资源有读和写的操作，就容易出现线程安全问题。
 *      1、局部变量不能共享：局部变量在每次调用方法时都是独立的，那么每个线程的run()的局部变量是独立的，不是共享数据。
 *      2、不同对象的实例变量不共享：不同的实例对象的实例变量是独立的。
 *      3、静态变量是共享的，类的静态变量被类的所有实例所共享。
 *      4、同一个对象的实例变量共享：尤其是使用同一个Runnable对象创建的多个Thread线程对象时，这些线程对象共享Runnable对象的实例变量。
 *      5、抽取资源类，共享同一个资源对象。
 *      下面代码中以三个窗口卖10张票举例。
 *
 * synchronized关键字：利用线程同步解决线程安全问题。
 *      要解决上述多线程并发访问一个资源的安全性问题：也就是解决重复票与不存在票问题，Java中提供了同步机制(synchronized)来解决。
 *
 *      同步机制原理：
 *          同步机制的原理，其实就相当于给某段代码加“锁”，任何线程想要执行这段代码，都要先获得“锁”，我们称它为同步锁。在任何时候,
 *          最多允许一个线程拥有同步锁，谁拿到锁就进入代码块，其他的线程只能在外等着(BLOCKED)。
 *
 *      同步代码块：
 *          synchronized关键字可以用于某个区块前面，表示只对这个区块的资源实行互斥访问。
 *          格式：
 *              synchronized(同步锁){
 *                  需要同步操作的代码
 *              }
 *
 *      同步方法：
 *          synchronized关键字直接修饰方法，表示同一时刻只有一个线程能进入这个方法，其他线程在外面等着。
 *          格式：
 *              public synchronized void method(){
 *                  可能会产生线程安全问题的代码
 *              }
 *
 *      synchronized锁是什么：
 *          同步锁对象可以是任意类型，但是必须保证竞争“同一个共享资源”的多个线程必须使用同一个“同步锁对象”。
 *              * 对于同步方法来说，同步锁对象只能是默认的：
 *                  - 静态方法：当前类的Class对象（类名.class）  --> 共享静态变量资源
 *                  - 非静态方法：this                         --> 共享同一对象的实例变量资源
 *              * 对于同步代码块来说，同步锁对象是由程序员手动指定的（很多时候也是指定为this或类名.class）
 *
 * 单例设计模式的线程安全问题
 *      饿汉式没有线程安全问题：在类初始化时就直接创建单例对象，而类初始化过程是没有线程安全问题的。
 *
 *      懒汉式具有线程安全问题：有可能第一个线程在刚刚判断实例为空时，发生了线程调度，此时实例为空，两个线程都创建了实例对象，不符合单例模式要求。
 *          利用同步锁可解决懒汉式的线程安全问题。
 *
 *      使用内部类：内部类只有在外部类被调用时才加载，产生实例；又不用加锁。此模式具有之前两种模式的优点，同时屏蔽它们的缺点，是最好的单例模式。
 *
 * 死锁：
 *      不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁。
 *      诱发死锁的原因：
 *          * 互斥访问
 *          * 忙着等待
 *          * 不可抢占
 *          * 循环等待
 *      以上4个条件同时出现就会触发死锁。
 *
 * @ClassName: CSynchronizedTest.java
 * @Author: anpeng
 * @Date: 2023/11/10 22:08
 */
@SuppressWarnings("all")
public class C_SynchronizedTest {
    //三个窗口去卖10张票
    //1、局部变量不能共享。
    @Test
    public void testLocalVariable() throws InterruptedException {
        LocalTicket lt1 = new LocalTicket("窗口1");
        LocalTicket lt2 = new LocalTicket("窗口2");
        LocalTicket lt3 = new LocalTicket("窗口3");
        lt1.start();
        lt2.start();
        lt3.start();
        //发现卖出 30 张票。
        Thread.sleep(3000);//等待子线程执行完毕
    }

    //2、不同对象的实例变量不共享
    @Test
    public void testInstanceVariable() throws InterruptedException {
        DifferentInstanceTicket dit1 = new DifferentInstanceTicket("窗口1");
        DifferentInstanceTicket dit2 = new DifferentInstanceTicket("窗口2");
        DifferentInstanceTicket dit3 = new DifferentInstanceTicket("窗口3");
        dit1.start();
        dit2.start();
        dit3.start();
        //发现卖出 30 张票。
        Thread.sleep(3000);
    }

    //3、静态变量是共享的
    @Test
    public void testStaticVariable() throws InterruptedException {
        StaticTicket st1 = new StaticTicket("窗口1");
        StaticTicket st2 = new StaticTicket("窗口2");
        StaticTicket st3 = new StaticTicket("窗口3");
        st1.start();
        st2.start();
        st3.start();
        //发现卖出近10张票,但票有重复或负数票问题，即线程安全问题
        Thread.sleep(3000);
    }

    //4、同一个对象的实例变量是共享的
    @Test
    @SuppressWarnings("all")
    public void testSameInstanceVariable() throws InterruptedException {
        SameInstanceTicket sit1 = new SameInstanceTicket();
        Thread t1 = new Thread(sit1,"窗口1");
        Thread t2 = new Thread(sit1,"窗口2");
        Thread t3 = new Thread(sit1,"窗口3");
        t1.start();
        t2.start();
        t3.start();
        //发现卖出近10张票,但票有重复或负数票问题，即线程安全问题
        Thread.sleep(3000);
    }

    //5、编写资源类，共享同一个资源对象
    @Test
    @SuppressWarnings("all")
    public void testResourceClass() throws InterruptedException {
        //2、创建资源对象
        Ticket ticket = new Ticket();
        //3、启动多个线程操作资源类的对象
        Thread t1 = new Thread("窗口一") {
            @Override
            public void run(){
                while (true) {
                    ticket.sale();
                }
            }
        };
        Thread t2 = new Thread("窗口二") {
            @Override
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        };
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        }, "窗口三");
        t1.start();
        t2.start();
        t3.start();
        //发现卖出近10张票，但是有重复或负数票问题，即线程安全问题。
        Thread.sleep(3000);
    }

    //三个窗口共同卖300张票，通过同步锁机制解决线程安全问题
    //6、静态方法加锁
    @Test
    public void testStaticSync() throws InterruptedException {
        StaticMethodSynchronized sms1 = new StaticMethodSynchronized("窗口1");
        StaticMethodSynchronized sms2 = new StaticMethodSynchronized("窗口2");
        StaticMethodSynchronized sms3 = new StaticMethodSynchronized("窗口3");
        sms1.start();
        sms2.start();
        sms3.start();

        Thread.sleep(3000);
    }

    //7、非静态方法加锁
    @Test
    @SuppressWarnings("all")
    public void testNoStaticMethodSynchronized() throws InterruptedException {
        NoStaticMethodSynchronized nsm = new NoStaticMethodSynchronized();
        Thread t1 = new Thread(nsm, "窗口1");
        Thread t2 = new Thread(nsm, "窗口2");
        Thread t3 = new Thread(nsm, "窗口3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(3000);
    }

    //8、代码块加锁
    @Test
    @SuppressWarnings("all")
    public void testCodeBlockSynchronized() throws InterruptedException {
        //创建资源类对象
        SynchronizedTicket st = new SynchronizedTicket();
        //创建并启动多个线程操作资源类对象
        Thread t1 = new Thread("窗口1"){
            @Override
            public void run(){//不能给run()直接加锁，因为t1,t2,t3的三个run方法分别属于三个Thread类对象
                //而且run方法是非静态方法，那么锁对象默认选 this，那么锁对象根本不是同一个
                while (true){
                    synchronized(st){//代码块加锁
                        st.sale();
                    }
                }
            }
        };
        Thread t2 = new Thread("窗口2"){
            @Override
            public void run(){//不能给run()直接加锁，因为t1,t2,t3的三个run方法分别属于三个Thread类对象
                //而且run方法是非静态方法，那么锁对象默认选 this，那么锁对象根本不是同一个
                while (true){
                    synchronized(st){//代码块加锁
                        st.sale();
                    }
                }
            }
        };
        Thread t3 = new Thread("窗口3"){
            @Override
            public void run(){//不能给run()直接加锁，因为t1,t2,t3的三个run方法分别属于三个Thread类对象
                //而且run方法是非静态方法，那么锁对象默认选 this，那么锁对象根本不是同一个
                while (true){
                    synchronized(st){//代码块加锁
                        st.sale();
                    }
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();

//        Thread.sleep(300);
        try {//替换上面的线程睡眠方法。
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //9、测试饿汉式单例模式是否有线程安全问题
    HungrySingle hs1 = null;//把hs1和hs2声明在外面，是想要在线程的匿名内部类中为 hs1 和 hs2 赋值
    HungrySingle hs2 = null;
    @Test
    @SuppressWarnings("all")
    public void testHungrySingle(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                hs1 = HungrySingle.getInstance();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                hs2 =HungrySingle.getInstance();
            }
        };
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println(hs1);
            System.out.println(hs2);
            System.out.println(hs1 == hs2);//true 即饿汉式单例模式不存在线程安全问题。
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //10、单线程下懒汉式没有线程安全问题
    @Test
    public void testSingleThreadLazy(){
        LazyOne l1 = LazyOne.getInstance();
        LazyOne l2 = LazyOne.getInstance();
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l1 == l2);//true，懒汉式在单线程模式下没有线程安全问题
    }

    //11、多线程下懒汉式存在线程安全问题
    LazyOne s1;//把s1和s2声明在外面，是想要在线程的匿名内部类中为s1和s2赋值
    LazyOne s2;
    @Test
    @SuppressWarnings("all")
    public void testMultiThreadLazy(){
        Thread t1 = new Thread(){
            public void run(){
                s1 = LazyOne.getInstance();
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                s2 = LazyOne.getInstance();
            }
        };
        t1.start();
        t2.start();

        try {//替代Thread.sleep()
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);//false，懒汉式在多线程下存在线程安全问题。
    }

    //12、利用同步锁机制解决多线程下懒汉式存在的线程安全问题
    @Test
    @SuppressWarnings("all")
    public void testMultiThreadLazy1(){
        Thread t1 = new Thread(){
            public void run(){
                s1 = LazyOne.getInstance1();
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                s2 = LazyOne.getInstance1();
            }
        };
        t1.start();
        t2.start();

        try {//替代Thread.sleep()
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);//true，利用同步锁机制解决多线程下懒汉式的线程安全问题
    }

    //13、利用内部类单例模式--内部类只有在外部类被调用时才加载，产生实例；又不用加锁，因为只读不写。最好的单例模式。
    InnerSingle obj1;
    InnerSingle obj2;
    @Test
    @SuppressWarnings("all")
    public void testInnerClassSingle(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                obj1 = InnerSingle.getInstance();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                obj2 = InnerSingle.getInstance();
            }
        };
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println(obj1);
            System.out.println(obj2);
            System.out.println(obj1 == obj2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
