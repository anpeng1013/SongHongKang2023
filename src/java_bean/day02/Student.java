package java_bean.day02;

/**
 * @ClassName: Student.java
 * @Author: anpeng
 * @Date: 2023/10/22 22:05
 */
public class Student extends Person{
    private String school;

    public Student(){}

    public Student(String school){
        this.school = school;
    }

    @Override
    public void speak(){
        System.out.println("My name is " + getName() + ", a student of " + getSchool() +
                " and " + getAge() + "years old.");
    }

    public void study(){
        System.out.println("I am a student, study in china.");
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
