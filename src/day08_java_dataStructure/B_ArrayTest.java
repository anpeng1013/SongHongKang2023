package day08_java_dataStructure;

import org.junit.Test;

/**
 * 数组：在java中，数组是用来存放同一种数据类型的集合，注意只能存放同一种数据类型。
 *      1、定义：
 *          - 只声明类型和长度：
 *              数据类型[] 数组名称 = new 数据类型[数组长度];
 *          - 声明类型和初始化：
 *              数据类型[] 数组名称 = {元素1，元素2，....};
 *      2、元素特点：
 *          - 基本数据 --> 值
 *          - 引用数据 --> 地址
 *      3、物理结构：
 *          - 申请内存：一次申请一大段连续的空间，一旦申请到了，内存大小就固定了。
 *          - 不能动态扩展（初始化给大了，浪费；给小了，不够用）。插入快，删除和查找慢。
 *          - 存储特点：所有数据存储在这个连续的空间中，数组中的每一个元素都是一个具体数据（或对象），数据紧密排布，不能有间隔。
 *
 *
 * @ClassName: B_ArrayTest.java
 * @Author: anpeng
 * @Date: 2024/1/17 18:45
 */
public class B_ArrayTest {
    @Test
    public void test01(){
        System.out.println("this is array");
    }

}
