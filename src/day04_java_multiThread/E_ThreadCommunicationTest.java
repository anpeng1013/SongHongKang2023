package day04_java_multiThread;

import java_bean.day04.Clerk;
import java_bean.day04.Consumer;
import java_bean.day04.Printer;
import java_bean.day04.Producer;
import org.junit.Test;

/**
 * 为什么要处理线程间通信：
 *      当需要多个线程来共同完成一件任务，并且希望它们有规律的执行，那么多个线程之间需要一些通信机制，可以协调它们的工作，以此实现多线程
 *      共同操作一份数据。比如：线程A用来生产包子的，线程B用来吃包子的，包子可以理解为同一资源，线程A与线程B处理的动作，一个是生产，一个
 *      是消费，此时B线程必须等到A线程完成后才能执行，那么线程A与线程B之间就需要线程通信，即--等待唤醒机制
 *
 * 等待唤醒机制：
 *      这是多个线程间的一种协作机制。谈到线程我们经常想到的是线程间的竞争(race)，比如去争夺锁，但这并不是故事的全部，线程间也会有协作机制。
 *      在一个线程满足某个条件时，就进入等待状态(wait() / wait(time))，等待其他线程执行完他们的指定代码过后再将其唤醒(notify()); 或可以指定
 *      wait的时间，等时间到了自动唤醒；在有多个线程进行等待时，如果需要，可以使用notifyAll()来唤醒所有的等待线程。wait/notify就是线程间的
 *      一种协作机制。
 *          1. wait：线程不再活动，不再参与调度，进入wait set中，因此不会浪费CPU资源，也不会去竞争锁了，这时的线程状态是 WAITING 或
 *          TIMED_WAITING。它还要等着别的线程执行一个特别的动作，也即“通知（notify）”或者等待时间到，在这个对象上等待的线程从wait set中
 *          释放出来，重新进入到调度队列（ready queue）中。
 *          2. notify：则选取所通知对象的 wait set 中的一个线程释放；
 *          3. notifyAll：则释放所通知对象的 wait set 上的全部线程。
 *          注意：
 *              被通知的线程被唤醒后也不一定能立即恢复执行，因为它当初中断的地方一般是在同步块内，而此刻它已经不持有锁，所以它需要再次尝试去
 *              获取锁（很可能面临其它线程的竞争），成功后才能在当初调用wait方法之后的地方恢复执行。
 *          总结：
 *              * 如果被唤醒后能获取锁，线程就从 WAITING 状态变成 RUNNABLE（可运行）状态；
 *              * 否则，线程就从 WAITING 状态又变成 BLOCKED（等待锁） 状态
 *          细节：
 *              1、wait方法与notify方法必须要由同一个锁对象调用。因为：对应的锁对象可以通过notify唤醒使用同一个锁对象调用的wait方法后的线程。
 *              2、wait方法与notify方法是属于Object类的方法的。因为：锁对象可以是任意对象，而任意对象的所属类都是继承了Object类的。
 *              3、wait方法与notify方法必须要在同步代码块或者是同步函数中使用。因为：必须要通过锁对象调用这两个方法。否则会报
 *                  java.lang.IllegalMonitorStateException 异常
 *
 * 生产者和消费者问题
 *      等待唤醒机制可以解决经典的“生产者与消费者”的问题。生产者与消费者问题（英语：Producer-consumer problem），也称有限缓冲问题
 *      （英语：Bounded-buffer problem），是一个多线程同步问题的经典案例。该问题描述了两个（多个）共享固定大小缓冲区的线程--即所谓的
 *      “生产者”和“消费者”--在实际运行时会发生的问题。生产者的主要作用是生成一定量的数据放到缓冲区中，然后重复此过程。与此同时，消费者也在
 *      缓冲区消耗这些数据。该问题的关键就是要保证生产者不会在缓冲区满时加入数据，消费者也不会在缓冲区中空时消耗数据。
 *
 *      举例：
 *          生产者(Producer)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，店员一次只能持有固定数量的产品(比如:20），如果
 *      生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者
 *      等一下，如果店中有产品了再通知消费者来取走产品。类似的场景，比如厨师和服务员等。
 *
 *      生产者与消费者问题中其实隐含了两个问题:
 *          * 线程安全问题[同步]：因为生产者与消费者共享数据缓冲区，产生安全问题。不过这个问题可以使用同步锁机制解决。
 *          * 线程协作问题[通信]：要解决该问题，就必须让生产者线程在缓冲区满时等待(wait)，暂停并进入阻塞状态，等到下次消费者消耗了缓冲区中
 *                          数据的时候，通知(notify)正在等待的生产者线程恢复到就绪状态，重新开始往缓冲区添加数据。同样，也可以让消费者线程
 *                          在缓冲区空时进入等待(wait)，暂停进入阻塞状态，等到生产者往缓冲区添加数据之后，再通知(notify)正在等待的消费者线程
 *                          恢复到就绪状态。通过这样的通信机制来解决此类问题。
 *
 * 面试题：区分sleep()和wait()
 *      相同点：一旦执行，都会使得当前线程结束执行状态，进入阻塞状态。
 *
 *      不同点：
 *          1、定义方法所属的类：sleep()在Thread类中定义，wait()在Object类中定义。
 *          2、使用范围的不同：sleep()可以在任何需要使用的位置被调用，wait()必须使用在同步代码块或同步方法中。
 *          3、都在同步结构中使用的时候，是否释放同步监视器的操作不同：sleep()不会释放同步监视器，wait()会释放同步监视器(锁)。
 *          4、结束等待的方式不同：sleep()指定时间一到就结束阻塞。 wait()可以指定时间也可以无限等待直到notify或notifyAll。
 *
 * 是否释放锁的操作
 *      任何线程进入同步代码块、同步方法之前，必须先获得对同步监视器的锁定，那么何时会释放对同步监视器的锁定呢？
 *
 *      释放锁的操作：
 *          * 当前线程的同步方法、同步代码块执行结束。
 *          * 当前线程在同步代码块、同步方法中遇到break、 return 终止了该代码块、或continue该方法的继续执行
 *          * 当前线程在同步代码块、同步方法中出现了未处理的 Error 或 Exception，导致当前线程异常结束。
 *          * 当前线程在同步代码块、同步方法中执行了锁对象的 wait()方法，当前线程被挂起，并释放锁。
 *
 *      不会释放锁的操作：
 *          * 线程执行同步代码块或同步方法时，程序调用 Thread.sleep()、Thread.yield()方法暂停当前线程的执行。
 *          * 线程执行同步代码块时，其他线程调用了该线程的 suspend() 方法将该该线程挂起，该线程不会释放锁（同步监视器）。
 *              应尽量避免使用 suspend()和 resume()这样的过时来控制线程。
 *
 * @ClassName: E_ThreadCommunication.java
 * @Author: anpeng
 * @Date: 2023/11/11 21:52
 */
@SuppressWarnings("all")
public class E_ThreadCommunicationTest {
    //1、wait和notify--使用两个线程打印1-10，线程1，线程2交替打印
    @Test
    public void testWaitAndNotify(){
        Printer printer = new Printer();
        Thread t1 = new Thread(printer,"线程1");
        Thread t2 = new Thread(printer,"线程2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("打印结束！");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //2、生产者和消费者问题--同步和通信
    @Test
    public void testProducerAndConsumer(){
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);

        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);

        producer.setName("生产者1");
        c1.setName("消费者1");
        c2.setName("消费者2");

        producer.start();
        c1.start();
        c2.start();

        try {
            producer.join();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
