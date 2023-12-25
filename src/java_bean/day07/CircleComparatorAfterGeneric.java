package java_bean.day07;

import java.util.Comparator;

/**
 * @ClassName: CircleComparatorAfterGeneric.java
 * @Author: anpeng
 * @Date: 2023/12/25 15:21
 */
public class CircleComparatorAfterGeneric implements Comparator<Circle>{
    @Override
    public int compare(Circle o1, Circle o2) {
        //不需要强制转换，代码更简洁
        return Double.compare(o1.getRadius(), o2.getRadius());
    }
}
