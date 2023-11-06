package java_bean.day03;

/**
 * @ClassName: Student1.java
 * @Author: anpeng
 * @Date: 2023/11/6 10:06
 */
@Table("t_stu")
@SuppressWarnings("unused")
public class Student1 {
    @Column(columnName = "sid",columnType = "int")
    private  int id;
    @Column(columnName = "sname", columnType = "varchar(20)")
    private String name;

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

    @Override
    public String toString() {
        return "Student1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
