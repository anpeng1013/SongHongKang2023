package javaBean.day03;

/**
 * @ClassName: Person.java
 * @Author: anpeng
 * @Date: 2023/4/9 12:44
 */
public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public Person(){}

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


    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
