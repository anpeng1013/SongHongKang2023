package java_bean.day03;

import junit.framework.TestCase;

/**
 * 这是Junit版本3的写法：
 *  1、需要继承junit.framework.TestCase抽象类
 *  2、无需使用@Test注解
 *  3、推荐使用版本4
 *
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
