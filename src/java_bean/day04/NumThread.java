package java_bean.day04;

import java.util.concurrent.Callable;

/**
 * 创建多线程的方式三：实现 Callable （jdk5.0 新增的）
 *
 * @ClassName: NumThread.java
 * @Author: anpeng
 * @Date: 2023/11/12 15:04
 */
//1、创建一个是实现Callable的实现类
@SuppressWarnings("all")
public class NumThread implements Callable {
    //2、实现call方法，将此线程需要执行的操作声明在call()方法中

    @Override
    public Object call(){//可以抛，也可以不抛异常
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
