package day04_java_multiThread;

import org.junit.Test;

/**
 * 程序、进程与线程
 *      程序(program)：为完成特定任务，用某种计算机语言编写的一组指令的集合。即指一段静态的代码，静态对象。
 *
 *      进程(process)：程序的一次执行过程，或是正在内存中运行的应用程序。如：运行中的QQ，运行中的网易云音乐播放器。
 *          * 每个进程都有一个独立的内存空间，即不同的进程之间是不共享内存的。
 *          * 系统运行一个程序即是一个进程从创建、运行到消亡的过程。（生命周期）
 *          * 程序是静态的，进程是动态的。
 *          * 进程作为操作系统调度和分配资源的最小单位（亦是系统运行程序的基本单位），系统在运行时会为每个进程分配不同的内存区域。
 *          * 现代操作系统，大多数支持多进程的，即支持同时运行多个程序。
 *
 *      线程(Thread)：进程可以进一步细化为线程，线程是程序内部的一条执行路径。一个进程中至少有一个线程。
 *          * 一个进程同一时间若并行执行多个线程，就是支持多线程的。
 *          * 线程是作为CPU调度和执行的最小单位。
 *          * 一个进程中的多个线程共享相同的内存单元，它们从同一堆中分配对象，可以访问相同的变量和对象。这就使得线程间通信更简便、高效。
 *              但多个线程操作共享的系统资源可能带来安全隐患。
 *          * 线程共享：堆和方法区[类信息，常量，静态变量]。线程独享：虚拟机栈、本地方法栈、程序计数器。
 *
 * 线程调度
 *      分时调度：所有线程轮流使用CPU的使用权，并且平均分配每个线程占用CPU的时间。
 *
 *      抢占式调度：让优先级较高的线程以较大的概率优先使用CPU。如果线程的优先级相同，那么会随机选择一个(线程随机性)，java使用抢占式调度。
 *
 * 多线程的优点：
 *      1、提高应用程序的响应。对图形化界面更有意义，可增强用户体验。
 *      2、提高计算机系统CPU的利用率。
 *      3、改善程序结构。将既长又复杂的进程分为多个线程，独立运行，利于理解和修改。
 *
 * 并行和并发：
 *      并行（parallel）：指两个或多个事件在同一个时刻（同时发生）。即在同一时刻，有多条指令在多个CPU上同时执行。
 *          比如：多个人同时做不同的事
 *
 *      并发（concurrency）：指两个或多个事件在同一个事件段内发生。即在同一段时间内，有多条指令在同一个CPU快速轮换交替执行，使得宏观上
 *          具有多个进程同时执行的效果。
 *
 *      在操作系统中启动多个程序，并发指的是在一段时间内宏观上有多个程序同时运行，这在单核CPU系统中，每一时刻只能有一个程序执行，即微观上
 *      这些程序是分时的交替运行，只不过是给人的感觉是同时运行，那是因为分时交替运行的时间是非常短的。而在多核CPU系统中，则这些可以并发执行
 *      的程序便可以分配到多个CPU内核上，实现多任务并行执行，即利用每个处理器来处理一个可以并发执行的程序，这样多个程序便可以同时执行。目前
 *      电脑市场上说的多核CPU，便是多核处理器，核越多，并行处理的程序越多，能大大的提高电脑运行的效率。
 *
 *
 * @ClassName: A_Concept.java
 * @Author: anpeng
 * @Date: 2023/11/8 17:26
 */
@SuppressWarnings("all")
public class A_ConceptTest {
    @Test
    public void test(){
        System.out.println("this is Thread concept");
    }
}
