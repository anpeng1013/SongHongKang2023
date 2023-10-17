package java_bean.day03;

import junit.framework.TestCase;

/**
 * @ClassName: PersonTest.java
 * @Author: anpeng
 * @Date: 2023/4/9 20:25
 */
public class PersonTest extends TestCase {

    public void testTestSpeak() {
        Person person = new Person();
        person.setAge(26);
        person.setName("anpeng");
        System.out.println("name = " + person.getName() + " age is " + person.getAge());
    }
}