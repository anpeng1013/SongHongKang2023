package java_bean.day03;

import junit.framework.TestCase;

/**
 * @ClassName: StudentTest.java
 * @Author: anpeng
 * @Date: 2023/10/23 9:56
 */
public class StudentTest extends TestCase {

    public void testSpeak() {
        Student student = new Student();
        student.setName("huli");
        student.setAge(24);
        student.setSchool("FJ Agriculture university");
        student.speak();
    }
}
