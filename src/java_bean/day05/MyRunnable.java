package java_bean.day05;

/**
 * @ClassName: MyRunnable.java
 * @Author: anpeng
 * @Date: 2023/11/10 11:02
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);//实现Runnable接口的对象中没有getName方法
        }
    }
}
