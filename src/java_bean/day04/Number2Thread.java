package java_bean.day04;

import java.util.concurrent.Callable;

/**
 * @ClassName: Number2Thread.java
 * @Author: anpeng
 * @Date: 2023/11/13 9:46
 */
@SuppressWarnings("all")
public class Number2Thread implements Callable {
    @Override
    public Object call() throws Exception {
        int evenSum = 0;//记录偶数的和
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                evenSum += i;
            }
        }
        return evenSum;
    }
}
