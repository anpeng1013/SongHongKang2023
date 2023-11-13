package java_bean.day04;

/**
 * @ClassName: Number1Thread.java
 * @Author: anpeng
 * @Date: 2023/11/13 9:43
 */
public class Number1Thread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
