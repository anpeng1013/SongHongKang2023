package java_bean.day03;

/**
 * @ClassName: Person.java
 * @Author: anpeng
 * @Date: 2023/4/9 12:44
 *
 * javaBean,是指符合如下标准的java类：
 *      ① JavaBean必须是公共的。
 *      ② JavaBean有一个不带参数的构造函数，如果public类的构造函数包含参数的话，那这个类不能做为JavaBean。
 *      ③ JavaBean属性默认是私有的，通过getProperty获取属性，通过setProperty设置属性。
 */
public class Person {
    private int age;
    private String name;

    public void speak(){
        System.out.println("my name is " + getName() +
                 ", and " + getAge() + " years old;");

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
