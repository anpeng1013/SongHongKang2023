package day04_java_multiThread;

import java_bean.day04.MyRunnable;
import java_bean.day04.MyThread;
import org.junit.Test;

/**
 * 创建和启动线程
 *      概述：
 *          * Java语言的JVM允许程序运行多个线程，使用java.lang.Thread类代表线程，所有线程对象都必须是Thread类或其子类的实例。
 *          * Thread类的特性：
 *              1、每个线程都是通过某个特定Thread对象的run()方法来完成操作的，因此把run()方法体称为线程执行体。
 *              2、需要通过该Thread对象的start()方法来启动这个线程，而非直接调用线程对象的run()方法。
 *              3、要想实现多线程，必须在主线程中创建新的线程对象。
 *
 *      两种基本方式：
 *          方式1：继承Thread类
 *              1、定义Thread类的子类，并重写该类的run()方法，该run()方法的方法体就代表了线程需要完成的任务。
 *              2、创建Thread子类的实例，即创建了新的线程对象。
 *              3、调用线程对象的start()方法来启动该线程，启动后会自动执行线程的run()方法。
 *              注意：
 *                  * 如果自己手动调用run()方法，那么就只是普通方法，没有启动多线程模式。
 *                  * run()方法由JVM调用，什么时候调用，执行的过程控制都由操作系统的CPU调度决定。
 *                  * 想要启动多线程，必须调用start()方法。
 *                  * 一个线程对象只能调用一次start()方法启动，如果重复调用了，则将抛出以上的异常“IllegalThreadStateException”。
 *
 *          方式2：实现Runnable接口
 *              Java有单继承的限制，当我们无法继承Thread类时，那么该如何做呢？在核心类库中提供了Runnable接口，我们可以实现Runnable接口，
 *              重写run()方法，然后再通过Thread类的对象代理启动和执行我们的线程体run()方法。步骤如下：
 *              1、定义Runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的线程执行体。
 *              2、创建Runnable实现类的实例，并以此实例作为Thread的target参数来创建Thread对象，该Thread对象才是真正的线程对象。
 *              3、调用线程对象的start()方法，启动线程。调用Runnable接口实现类的run方法。
 *              注意：
 *                  * 通过实现 Runnable 接口，使得该类有了多线程类的特征。所有的分线程要执行的代码都在run方法里面。
 *                  * 在启动多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target)构造出对象，然后调用Thread对象的
 *                      start()方法来运行多线程代码。
 *                  * 实际上，所有的多线程代码都是通过运行Thread的start()方法来运行的。 因此，不管是继承Thread类还是实现Runnable接口来
 *                      实现多线程，最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。
 *                  * Runnable对象仅仅作为Thread对象的target，Runnable实现类里包含的run()方法仅作为线程执行体。而实际的线程对象
 *                  依然是Thread实例，只是该Thread线程负责执行其target的run()方法。
 *
 *          变形写法：
 *              使用匿名内部类对象来实现线程的创建和启动。
 *
 *      两种方式的对比：
 *          联系：Thread类实际上也是实现了Runnable接口的类。
 *          区别：
 *               * 继承Thread：线程代码存放在Thread子类的run方法中。
 *               * 实现Runnable：线程代码存在接口的实现子类的run方法中。
 *
 *      实现Runnable接口比继承Thread类所具有的优势
 *           * 避免单继承的局限性
 *           * 多个线程可以共享同一个接口实现类的对象，非常适合多个相同线程来处理同一份资源。
 *           * 增加程序的健壮性，实现解耦操作，代码可以被多个线程共享，代码和线程独享。
 *
 *       JDK1.5以后还可以使用实现Callable接口和线程池来创建启动新的线程。请注意，四种方式都必须是Thread类或其子类来启动新的线程。
 *
 * Thread类的常用结构：
 *      构造器：
 *          * public Thread(): 分配一个新的线程对象。
 *          * public Thread(String name): 分配一个指定名字的新的线程对象。
 *          * public Thread(Runnable target): 指定创建线程的目标对象，它实现了Runnable接口中的run方法
 *          * public Thread(Runnable target,String name): 分配一个带有指定目标的线程对象并指定名字
 *
 *      常用方法系列1：
 *          * public void run(): 此线程要执行的任务在此处定义代码。
 *          * public void start(): 此线程开始执行; Java虚拟机调用此线程的run方法。
 *          * public String getName(): 获取当前线程名称。
 *          * public void setName(String name)：设置该线程名称。
 *          * public static Thread currentThread(): 返回对当前正在执行的线程对象的引用。在Thread子类中就是this，
 *                                                  通常用于主线程和Runnable实现类。
 *          * public static void sleep(long millis): 使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行）。
 *          * public static void yield()：yield只是让当前线程暂停一下，让系统的线程调度器重新调度一次，希望优先级与当前线程相同或
 *                                      更高的其他线程能够获得执行机会，但是这个不能保证，完全有可能的情况是，当某个线程调用了yield方法
 *                                      暂停之后，线程调度器又将其调度出来重新执行。
 *
 *      常用方法系列2：
 *          * public final boolean isAlive()：测试线程是否处于活动状态。如果线程已经启动且尚未终止，则为活动状态。
 *          * void join()：等待调用该方法的线程终止后，当前线程才开始执行。
 *              - void join(long millis)：等待该线程终止的时间最长为millis毫秒。如果millis时间到，将不再等待。
 *              - void join(long millis, int nanos)：等待该线程终止的时间最长为millis毫秒 + nanos纳秒。
 *          * public final void stop()：已过时，不建议使用。强行结束一个线程的执行，直接进入死亡状态。run()即刻停止，可能会导致一些
 *                      清理性的工作得不到完成，如文件，数据库等的关闭。同时，会立即释放该线程所持有的所有的锁，导致数据得不到同步的处理，
 *                      出现数据不一致的问题。
 *          * void suspend() / void resume(): 这两个操作就好比播放器的暂停和恢复。二者必须成对出现，否则非常容易发生死锁。
 *                      suspend()调用会导致线程暂停，但不会释放任何锁资源，导致其它线程都无法访问被它占用的锁，直到调用resume()。
 *                      已过时，不建议使用。
 *      常用方法系列3：
 *          每个线程都有一定的优先级，同优先级线程组成先进先出队列（先到先服务），使用分时调度策略。优先级高的线程采用抢占式策略，获得较多的
 *          执行机会。每个线程默认的优先级都与创建它的父线程具有相同的优先级。
 *              * Thread类的三个优先级常量：
 *                  - MAX_PRIORITY(10)：最高优先级
 *                  - MIN_PRIORITY(1)：最低优先级
 *                  - NORM_PRIORITY(5)：普通优先级，默认情况下main线程具有普通优先级。
 *              * public final int getPriority()：返回线程优先级
 *              * public final void setPriority(int newPriority)：改变线程的优先级，范围在[1, 10]之间。
 *
 *      守护线程（daemon）：
 *          * 有一种线程，它是在后台运行的，它的任务是为其他线程提供服务的，这种线程被称为“守护线程”。 JVM 的垃圾回收线程就是典型的守护线程。
 *          * 守护线程有个特点，就是如果所有非守护线程都死亡，那么守护线程自动死亡。形象理解：兔死狗烹，鸟尽弓藏
 *          * 调用setDaemon(true)方法可将指定线程设置为守护线程。必须在线程启动之前设置，否则会报IllegalThreadStateException异常。
 *          * 调用isDaemon()可以判断线程是否是守护线程
 *
 * 线程的生命周期：
 *      1、JDK1.5之前：新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）、死亡（Dead）共5种状态。
 *          * 新建（New）
 *              当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态。此时它和其他Java对象一样，仅仅由JVM为其分配了内存，
 *              并初始化了实例变量的值。此时的线程对象并没有任何线程的动态特征，程序也不会执行它的线程体run()。
 *
 *          * 就绪（Runnable）
 *              当线程对象调用了start()方法之后，线程就从新建状态转为就绪状态。JVM会为其创建方法调用栈和程序计数器，当然，处于这个状态中的
 *              线程并没有开始运行，只是表示已具备了运行的条件，随时可以被调度。至于什么时候被调度，取决于JVM里线程调度器的调度。
 *                  注意：程序只能对新建状态的线程调用start()，并且只能调用一次，如果对非新建状态的线程，如已启动的线程或已死亡的线程调用
 *                      start()都会报错IllegalThreadStateException异常。
 *
 *          * 运行（Running）
 *              处于就绪状态的线程获得CPU调度，开始执行run方法的线程体代码，则该线程处于运行状态。如果计算机只有一个CPU核心，在任何时刻
 *              只有一个线程处于运行状态，如果计算机有多个核心，将会有多个线程并行(Parallel)执行。
 *
 *          * 阻塞（Blocked）
 *              当在运行过程中的线程遇到如下情况时，会让出CPU并临时中止自己的执行，进入阻塞状态：
 *                  * 线程调用了sleep()方法，主动放弃所占用的CPU资源；
 *                  * 线程试图获取一个同步监视器，但该同步监视器正被其他线程持有；
 *                  * 线程执行过程中，同步监视器调用了 wait()，让它等待某个通知（notify）；
 *                  * 线程执行过程中，同步监视器调用了 wait(time)。
 *                  * 线程执行过程中，遇到了其他线程对象的加塞（join）；
 *                  * 线程被调用suspend方法被挂起（已过时，因为容易发生死锁）；
 *              当前正在执行的线程被阻塞后，其他线程就有机会执行了。针对如上情况，当发生如下情况时会解除阻塞，让该线程重新进入就绪状态，
 *              等待线程调度器再次调度它：
 *                  * 线程的sleep()时间到；
 *                  * 线程成功获得了同步监视器；
 *                  * 线程等到了通知(notify)；
 *                  * 线程wait的时间到了
 *                  * 加塞的线程结束了；
 *                  * 被挂起的线程又被调用了resume恢复方法（已过时，因为容易发生死锁）；
 *
 *          * 死亡（Dead）
 *              线程会以下面三种方式之一，结束后的线程就处于死亡状态：
 *                  * run方法执行完成，线程正常结束
 *                  * 线程执行过程中抛出了一个未捕获的异常（Exception）或错误（Error）
 *                  * 直接调用该线程的stop方法来结束该线程（已过时）。
 *
 *      2、JDK1.5之后：NEW(新建)、RUNNABLE(可运行)、Terminated(被终止)、BLOCKED(锁阻塞)、TIMED_WAITING(计时等待)、WAITING(无限等待)
 *          * NEW(新建)：线程刚被创建，但是并未启动，即还没调用 start 方法。
 *          * RUNNABLE(可运行)：这里没有区分就绪和运行状态。因为对于Java对象来说，只能标记为可运行，至于什么时候运行，不是JVM来控制的了，
 *                      是OS来进行调度的，而且时间非常短暂，因此对于Java对象的状态来说，无法区分。
 *          * Terminated(被终止)：表明此线程已经结束生命周期，终止运行。
 *          * BLOCKED(锁阻塞)：正在等待一个监视器锁(对象)的状态，只有获得锁对象的线程才能有执行机会。比如，线程A与线程B代码中使用同一个锁，
 *                      如果线程A获取到锁，线程A进入到Runnable状态，那么线程B就进入到Blocked锁阻塞状态。
 *          * TIMED_WAITING(计时等待)：一个正在限时等待另一个线程执行一个（唤醒）动作的线程处于这一状态。当前线程执行过程中遇到Thread类的
 *                      sleep或join，Object类的wait， LockSupport类的park方法，并且在调用这些方法时，设置了时间，那么当前线程会进入
 *                      TIMED_WAITING，直到时间到，或被中断。
 *          * WAITING(无限等待)：一个正在无限期等待另一个线程执行一个特别的（唤醒）动作的线程处于这一状态。当前线程执行过程中遇到遇到Object类
 *                      的wait，Thread类的join，LockSupport类的park方法，并且在调用这些方法时，没有指定时间，那么当前线程会进入WAITING状态，
 *                      直到被唤醒。
 *                      - 通过Object类的wait进入WAITING状态的要有Object的notify/notifyAll唤醒；
 *                      - 通过Condition的await进入WAITING状态的要有Condition的signal方法唤醒；
 *                      - 通过LockSupport类的park方法进入WAITING状态的要有LockSupport类的unpark方法唤醒
 *                      - 通过Thread类的join进入WAITING状态，只有调用join方法的线程对象结束才能让当前线程恢复；
 *                      说明：
 *                          当从WAITING或TIMED_WAITING恢复到Runnable状态时，如果发现当前线程没有得到监视器锁，那么会立刻转入BLOCKED状态。
 *
 *
 * @ClassName: BThreadTest.java
 * @Author: anpeng
 * @Date: 2023/11/9 17:01
 */
@SuppressWarnings("all")
public class B_ThreadTest {
    //方式1：继承Thread类
    @Test
    @SuppressWarnings("all")
    public void testThread() throws InterruptedException {
        //1、创建线程对象
        MyThread mt1 = new MyThread("子线程1");
        MyThread mt2 = new MyThread("子线程2");
        //2、开启并执行线程
        mt1.start();
        mt2.start();
        //3、在主方法线程中执行for循环
        for (int i = 0; i < 10; i++) {
            mt1.join();
            System.out.println("main线程！" + i);
        }

        Thread.sleep(5000);//Junit不支持多线程，主线程结束后，JVM就会调用System.exit(0)终止程序，通过在主线程中调用线程睡眠函数，
        //等待子线程执行完毕后再终止程序，也可以使用死循环等待，但需要手动终止程序。
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {//主函数中支持多线程。JVM会等待子线程执行完毕后再终止程序。
        //1、创建线程对象
        MyThread mt1 = new MyThread("子线程1");
        MyThread mt2 = new MyThread("子线程2");
        //2、开启并执行线程
        mt1.start();
        mt2.start();
        //3、在主方法线程中执行for循环
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程！" + i);
        }

        System.out.println(Thread.currentThread().getName() + "是否守护线程：" + Thread.currentThread().isDaemon());
    }

    //方式2：实现Runnable接口
    @Test
    public void testRunnable() throws InterruptedException {
        //1、创建接口类实例-任务对象
        MyRunnable mr = new MyRunnable();
        //2、创建任务对象
        Thread t = new Thread(mr, "长江");
        Thread t1 = new Thread(mr, "长城");
        t.start();
        t1.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("黄河 " + i);
        }

        Thread.sleep(5000);//等待子线程执行完毕
    }

    //变形写法：使用匿名内部类对象来实现线程的创建和启动
    @Test
    @SuppressWarnings("all")
    public void testAnonymous() throws InterruptedException {
        //1、继承Thread类
        new Thread("新的线程"){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + ": 正在执行" + i);
                }
            }
        }.start();

        //2、实现Runnable接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);//实现Runnable接口的对象中没有getName方法
                }
            }
        }, "最新线程").start();

        Thread.sleep(5000);//等待子线程执行完毕
    }

    //测试常用方法
    @Test
    @SuppressWarnings("all")
    public void testMethod() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "的优先级：" + Thread.currentThread().getPriority());
                System.out.println(Thread.currentThread().getName() + "是否守护线程：" + Thread.currentThread().isDaemon());
            }
        }, "my thread");
        thread.setPriority(8);
        thread.start();
        System.out.println(Thread.currentThread().getName() + "的优先级：" + Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getName() + "是否守护线程：" + Thread.currentThread().isDaemon());
        Thread.sleep(5000);//等待子线程执行完毕
    }
}
