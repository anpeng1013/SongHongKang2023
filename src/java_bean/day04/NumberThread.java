package java_bean.day04;

/**
 * @ClassName: NumberThread.java
 * @Author: anpeng
 * @Date: 2023/11/13 9:41
 */
public class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
