package day08_java_dataStructure;

import org.junit.Test;

/**
 * 链表：存储结点由数据域和指针域组成
 *      - 逻辑结构：线性结构。
 *      - 物理结构：不要求连续的存储空间。
 *      - 存储特点：链表由一系列结点组成，结点可以在代码执行过程中创建。每个结点包括两个部分：数据域和指针域。
 *      - 常见的链表：单链表、双链表、循环单链表、循环双链表
 *
 *      特点：
 *          - 使用不连续的内存空间，节省内存空间
 *          - 不需要提前声明好指定大小的内存空间。一次申请一小块内存，按需申请。
 *          - 数据的删除和插入很方便，不需要移动大量的数据。
 *          - 设计较为麻烦，查找数据必须按顺序查找，效率较慢。
 *
 * @ClassName: C_LinkedListTest.java
 * @Author: anpeng
 * @Date: 2024/1/19 18:00
 */
public class C_LinkedListTest {
    @Test
    public void test01(){
        System.out.println("this is linkedList");
    }

}
