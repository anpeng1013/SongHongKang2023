package java_bean.day05;

import java.util.Comparator;

/**
 * @ClassName: StudentScoreComparator.java
 * @Author: anpeng
 * @Date: 2023/11/17 16:24
 */
public class StudentScoreComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Student student1 = (Student) o1;
        Student student2 = (Student) o2;
        int result = student1.getScore() - student2.getScore();
        return result != 0 ? result : student1.getId() - student2.getId();//分数相同的情况下按学号排，学号肯定不会相同。
    }
}
