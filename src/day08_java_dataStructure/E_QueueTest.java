package day08_java_dataStructure;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 特点：
 *      - 队列（Queue）是只允许在一端进行插入，而在另一端进行删除的运算受限的线性表。
 *      - 队列是逻辑结构，其物理结构可以是数组，也可以是链表。
 *      - 队列的修改原则：队列的修改是依先进先出（FIFO）的原则进行的。新来的成员总是加入队尾（即不允许"加塞"），每次离开的成员总是
 *          队列头上的（不允许中途离队），即当前"最老的"成员离队。
 *      - 核心类库中的队列结构有：Linkedlist(实现了Queue接口)、ListedBlockingQueue(实现了BlockingQueue接口)、
 *                              PriorityQueue(实现了Queue接口)
 *
 * 操作：
 *      - 加入元素到队列的队尾：add(Object obj)/offer(Object obj)。
 *      - 获取队列的头部元素，不删除该元素element()/peek()。
 *      - 获取队列的头部元素，并删除该元素remove()/poll()。
 *      - 清空队列的全部元素：clear()。
 *      - 获取队列的元素个数：size()。
 *      - 判读队列是否为空：isEmpty()。
 *
 * 应用：
 *      - 图形的广度优先查找算法。
 *      - 可用于计算机各种事情处理的模拟：如事情队列、消息队列。
 *      - CPU的作业调度。
 *
 * 特殊队列：
 *      - 环形队列：队尾指针不能超过队头指针。
 *      - 双向队列：队列的两端都可以进行压入和弹出操作。
 *      - 阻塞队列：向已满的队列中插入元素时会阻塞，向空队列中取元素时也会阻塞；阻塞队列被设计主要用于生产者-消费者队列。
 *      - 优先队列：队列的元素按自然顺序排序，或者根据提供的Comparator进行排序。也就是说，优先级队列种的元素都是经过排序的。
 *      - 延时队列：延时队列中的元素都有一个有效期，只有当过了有效期才可以使用该元素。
 *
 *
 * @ClassName: E_Queue.java
 * @Author: anpeng
 * @Date: 2024/2/29 13:26
 */
@SuppressWarnings("all")
public class E_QueueTest {
    @Test
    public void test01 (){
        Queue<String> queue = new LinkedList<>();
        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        PriorityQueue priorityQueue = new PriorityQueue();
    }

}
