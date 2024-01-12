package java_bean.day07;

/**
 * @ClassName: Student.java
 * @Author: anpeng
 * @Date: 2024/1/12 16:23
 */
@SuppressWarnings("all")
public class Student extends Person{
    private String name;
    private int age;

    public Student(Object info, String name, int age) {
        super(info);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
