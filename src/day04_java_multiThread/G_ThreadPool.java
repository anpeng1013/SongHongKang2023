package day04_java_multiThread;

import java_bean.day04.Number1Thread;
import java_bean.day04.Number2Thread;
import java_bean.day04.NumberThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程创建方式四：使用线程池（JDK1.5新增的）
 *      问题：
 *          如果并发的线程数量很多，并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程
 *          和销毁线程需要时间。那么有没有一种办法使得线程可以复用，即执行完一个任务，并不被销毁，而是可以继续执行其他的任务？
 *
 *      思路：
 *          提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
 *
 *      好处：
 *          * 提高响应速度（减少了创建新线程的时间）。
 *          * 降低资源消耗（重复利用线程池中线程，不需要每次都创建）。
 *          * 便于线程管理：
 *              - corePoolSize：核心池的大小。
 *              - maximumPoolSize：最大线程数
 *              - keepAliveTime：线程没有任务时最多保持多长时间后会终止。
 *
 * 线程池相关API
 *      * JDK5.0之前，我们必须手动自定义线程池。从JDK5.0开始， Java内置线程池相关的API。在java.util.concurrent包下提供了线程池相关API：
 *          ExecutorService 和Executors。
 *
 *      * ExecutorService：真正的线程池接口。常见子类 ThreadPoolExecutor
 *          - void execute(Runnable command)：执行任务/命令，没有返回值，一般用来执行Runnable。
 *          - <T> Future</T> submit(Callable<T> task)：执行任务，有返回值，一般用来执行Callable。
 *          - void shutdown()：关闭连接池。
 *
 *      * Executors：一个线程池的工厂类，通过此类的静态工厂方法可以创建多种类型的线程池对象。
 *          - Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
 *          - Executors.newFixedThreadPool(int nThreads); 创建一个可重用的固定线程数量的线程池
 *          - Executors.newSingleThreadExecutor() ：创建一个只有一个线程的线程池
 *          - Executors.newScheduledThreadPool(int corePoolSize)：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
 *
 *
 * @ClassName: G_ThreadPool.java
 * @Author: anpeng
 * @Date: 2023/11/12 15:25
 */
public class G_ThreadPool {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //1、提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        System.out.println(service.getClass());//ThreadPoolExecutor
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        executor.setMaximumPoolSize(50);//设置线程池的属性--线程池中线程数的上限

        //2、执行指定的线程操作，需要提供实现Runnable接口或Callable接口实现类的对象
        executor.execute(new NumberThread());//使用service.execute也可以，service多态，父接口指针，指向子实现类对象。
        executor.execute(new Number1Thread());
        try {
            Future future = service.submit(new Number2Thread());
            System.out.println(future.getClass());//class java.util.concurrent.FutureTask
            System.out.println("偶数总和为：" + future.get());
        }catch (Exception e){
            e.printStackTrace();
        }

        //3、关闭线程池
        service.shutdown();
    }
}
