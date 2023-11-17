package java_bean.day05;

/**
 * @ClassName: Student.java
 * @Author: anpeng
 * @Date: 2023/11/16 15:37
 */
@SuppressWarnings("all")
public class Student implements Comparable{
    private int id;
    private String name;
    private int score;
    private int age;

    public Student(int id, String name, int score, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        //需要强制将o对象向下转型为Student类型的变量，才能调用Student类的中的属性
        Student student = (Student) o;
        //按照学号比较大小
        return this.id - student.id;
    }
}
