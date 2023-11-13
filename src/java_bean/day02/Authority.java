package java_bean.day02;

import java.util.Objects;

/**
 * @ClassName: Authority.java
 * @Author: anpeng
 * @Date: 2023/10/31 16:52
 */
public class Authority implements Cloneable{
    private int id;
    private int grade;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id == authority.id && grade == authority.grade && Objects.equals(description, authority.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, description);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
