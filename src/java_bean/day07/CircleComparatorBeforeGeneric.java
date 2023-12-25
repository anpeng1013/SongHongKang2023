package java_bean.day07;

import java.util.Comparator;

/**
 * @ClassName: CircleComparatorBeforeGeneric.java
 * @Author: anpeng
 * @Date: 2023/12/25 15:13
 */
public class CircleComparatorBeforeGeneric implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        //强制类型转换
        Circle c1 = (Circle) o1;
        Circle c2 = (Circle) o2;
        return Double.compare(c1.getRadius(), c2.getRadius());
    }
}
