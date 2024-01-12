package java_bean.day07;

/**
 * @ClassName: Person.java
 * @Author: anpeng
 * @Date: 2023/12/25 16:28
 */
@SuppressWarnings("all")
public class Person<T> {
    //使用T类型定义变量
    private T info;

    //使用T类型定义方法
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    //使用T类型定义构造器

    public Person(T info) {
        this.info = info;
    }

    //static方法中不能使用类的泛型
//    public static void show(T info) {//会报错，
//        System.out.println(info);
//    }

    //但是，静态方法可以使用自己的泛型
    public static <V> void show(V info) {
        System.out.println("info = " + info);
    }

    @Override
    public String toString() {
        return "Person{" +
                "info=" + info +
                '}';
    }
}
