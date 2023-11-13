package day04_java_multiThread;

import java_bean.day04.NumThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建方式三：实现Callable接口（JDK1.5新增）
 *      * 与使用Runnable相比，Callable功能更强大些
 *          - 相比run()方法，Callable接口的call()方法可以有返回值。
 *          - Callable接口的call()方法可以抛出异常
 *          - 支持泛型的返回值(需要借助FutureTask类，获取返回结果)
 *
 *      * Future接口（了解）
 *          - 可以对具体 Runnable、Callable 任务的执行结果进行取消、查询是否完成、获取结果等。
 *          - FutureTask 是 Future 接口的唯一的实现类。
 *          - FutureTask 同时实现了Runnable, Future接口。它既可以作为Runnable被线程执行，又可以作为 Future 得到 Callable 的返回值。
 *
 *      * 缺点
 *          在获取分线程执行结果的时候，当前线程（或是主线程）受阻塞，效率较低。
 *
 * @ClassName: F_Callable.java
 * @Author: anpeng
 * @Date: 2023/11/12 14:55
 */
public class F_Callable {
    public static void main(String[] args) {
        //3、创建Callable接口实现类的实例
        NumThread numThread = new NumThread();

        //4、将此Callable接口实现类的实例作为参数传递到FutureTask构造器中，创建FutureTask对象
        @SuppressWarnings("all")
        FutureTask futureTask = new FutureTask(numThread);

        //5、将FutureTask对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();

        //6、获取Callable中的call方法的返回值
        try {
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()方法的返回值。
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}
