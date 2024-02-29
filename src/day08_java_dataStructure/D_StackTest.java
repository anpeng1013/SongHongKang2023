package day08_java_dataStructure;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 栈：遵循先进后出的数据结构
 *
 * 特点：
 *      - 栈（Stack）又称为堆栈，是仅在表的一端进行插入和删除运算的线性表。
 *      - 栈，按照先进后出（FILO，first in last out）的原则存储数据，先进入的数据被压入栈底，最后的数据在栈顶。
 *            每次删除（退栈）的总是删除当前栈中最后压入的数据。而最先插入的在栈底，要在最后才能被删除。
 *      - 核心类库中的栈结构主要有 Stack 和 Linkedlist。
 *          * Stack 就是顺序栈，它是 Vector 的子类。
 *          * LinkedList 是链式栈。
 *
 * 操作：
 *      - peek()方法：查看栈顶元素，不弹出。
 *      - pop()方法：弹出栈顶元素。
 *      - push(E e)方法：压入栈顶元素。
 *      - clear()方法：清空栈内元素。
 *      - size()方法：返回栈内元素的个数。
 *      - empty()方法：判断栈内元素是否为空。
 *
 * 应用：
 *      - 二叉树和森林的遍历
 *      - CPU的中断处理
 *      - 图形的深度优先查找法（DFS）
 *      - 递归调用（斐波那契数列、汉诺塔问题）
 *      - 子程序的调用
 *
 * @ClassName: D_StackTest.java
 * @Author: anpeng
 * @Date: 2024/1/28 21:49
 */
@SuppressWarnings("all")
public class D_StackTest {
    @Test
    public void testStack(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1024);
        stack.push(1124);
        stack.push(1115);
        stack.push(7617);

        System.out.println("stack = " + stack);
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println("stack.peek() = " + stack.peek());

        while (!stack.empty()){
            System.out.println("stack.pop() = " + stack.pop());
        }
        System.out.println("stack = " + stack);//[]
    }

    @Test
    public void testLinkedList(){
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        System.out.println("list = " + list);
        System.out.println("list.peek() = " + list.peek());
        System.out.println("list.peek() = " + list.peek());

        while (!list.isEmpty()){
            System.out.println("list.pop() = " + list.pop());
        }
        System.out.println("list = " + list);
    }

}
