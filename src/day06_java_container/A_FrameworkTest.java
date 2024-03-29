package day06_java_container;

import org.junit.Test;

/**
 * java中的容器--集合框架
 *      1、数组的特点与弊端
 *          * 一方面，面向对象语言对事物的体现都是以对象的形式，为了方便对多个对象的操作，就要将对象进行存储。
 *          * 另一方面，使用数组存储对象方面具有一些弊端，而 Java 集合就像一种容器，可以动态地把多个对象的引用放入容器中。
 *          * 数组在内存存储方面的特点：
 *              - 数组初始化以后，长度就确定了
 *              - 数组中添加的元素是一次紧密排列的，有序的，可以重复的。
 *              - 数组声明的类型，就决定了进行元素初始化时的类型。不是此类型的变量，就不能添加。
 *              - 可以存储基本数据类型，也可以存储引用数据类型的变量。
 *          * 数组在存储数据方面的弊端：
 *              - 数据初始化后，长度就不可变了，不便于扩展
 *              - 数组中提供的属性和方法少，不便于进行添加、删除、插入、获取元素个数等操作，且效率不高。
 *              - 数组存储数据的特点单一，只能存储有序的、可以重复的数据。
 *          * java集合框架中的类可以存储多个不同类型的对象，还可以用于保存具有映射关系的关联数组。
 *
 *      2、java集合框架体系
 *          java集合可以分为 Collection 和 Map 两大体系：
 *              * Collection接口：用于存储一个一个的数据，也称为单列数据集合。
 *                  - List子接口：用来存储有序的、可以重复的数据（主要用来替换数组），相当于长度可变的“动态数组”。
 *                      -- 实现类：ArrayList（主要实现类）、LinkedList、Vector
 *
 *                  - Set子接口：用来存储无序的，不可重复的数据（类似于数学中的集合）
 *                      -- 实现类：HashSet（主要实现类）、LinkedHashSet、TreeSet
 *
 *                  - Queue/Deque子接口：java提供的标准队列结构的实现，支持先入先出（First-in-First-out）。
 *                      -- 实现类：LinkedBlockingQueue（主要实现类）、LinkedList（既是链表，又是队列）、PriorityQueue
 *
 *              * Map接口：用于存储具有"key-value"映射关系的键值对的集合，即一对一对的数据，也称双列数据集合。
 *                  -- 实现类：HashMap（主要实现类）、HashTable、LinkedHashMap、TreeMap、Properties。
 *
 * @ClassName: A_Framework.java
 * @Author: anpeng
 * @Date: 2023/11/17 21:32
 */
@SuppressWarnings("all")
public class A_FrameworkTest {
    @Test
    public void testArray(){
        int[] array = new int[5];//数组整型默认是零，因为数组在堆中，堆中数据有默认初始化，栈中数据没有默认初始化。
        for (int i : array) {
            System.out.println(i);
        }
    }

}
