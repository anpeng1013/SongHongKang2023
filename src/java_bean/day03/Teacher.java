package java_bean.day03;

/**
 * @ClassName: Teacher.java
 * @Author: anpeng
 * @Date: 2023/10/30 10:26
 */
public class Teacher extends Person{
    @Override
    public void speak(){
        System.out.println("I am a teacher.");
    }

    public static void walk(){
        System.out.println("I want to walk.");
    }

    public void teach(){
        System.out.println("I am teaching in the classroom");
    }
}
