package java_bean.day02;

import junit.framework.TestCase;

/**
 * 这是Junit版本3的写法：
 *   1、需要继承junit.framework.TestCase抽象类
 *   2、无需使用@Test注解
 *   3、推荐使用版本4
 *
 * @ClassName: PersonTest.java
 * @Author: anpeng
 * @Date: 2023/4/9 20:25
 */
public class PersonTest extends TestCase {

    public void testSpeak() {
        Person person = new Person();
        person.setAge(26);
        person.setName("anpeng");
        System.out.println("name = " + person.getName() + " age is " + person.getAge());
    }
}