package javaBean.day03;

import junit.framework.TestCase;

/**
 * @ClassName: PersonTest.java
 * @Author: anpeng
 * @Date: 2023/4/9 20:25
 */
public class PersonTest extends TestCase {

    public void testSpeak() {
        new Person(22,"huli").speak();
    }

    public void testGetAge() {
        System.out.println(new Person(23, "anpeng").getName());
    }

    public void testGetName() {
        System.out.println(new Person(22, "huli").getAge());
    }

    public void testSetAge(){
        Person anpeng = new Person();
        anpeng.setAge(28);
        System.out.println(anpeng.getAge());
    }

    public void testSetName(){
        Person person = new Person();
        person.setName("huli");
        System.out.println(person.getName());
    }
}