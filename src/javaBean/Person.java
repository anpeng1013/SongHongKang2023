package javaBean;

/**
 * @ClassName: Person.java
 * @Author: anpeng
 * @Date: 2023/4/9 12:44
 */
public class Person {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void speak(){
        System.out.println("my name is " + this.getName() +
                 ", and " + this.getAge() + " years old;");

    }
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
