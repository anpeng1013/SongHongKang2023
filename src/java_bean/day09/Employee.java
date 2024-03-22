package java_bean.day09;

import java.io.Serializable;

/**
 * @ClassName: Employee.java
 * @Author: anpeng
 * @Date: 2024/3/22 21:04
 */
@SuppressWarnings("all")
public class Employee implements Serializable {
    private static final long serialVersionUID = 234242343243L; //它的值由程序员随意指定即可，建议显示声明。
    public static String company; // static修饰的静态变量，不会被序列化。因为静态变量不属于某个对象。
    public String name;
    public String address;
    public transient int age; //transient暂态修饰成员，不会被序列化

    public Employee(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public static String getCompany() {
        return company;
    }

    public static void setCompany(String company) {
        Employee.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
