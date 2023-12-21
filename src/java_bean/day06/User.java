package java_bean.day06;

/**
 * @ClassName: User.java
 * @Author: anpeng
 * @Date: 2023/12/21 11:31
 */
@SuppressWarnings("all")
public class User implements Comparable {
    String name;
    int age;

    public User(String name, int age) {
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
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(this == o){
            return 0;
        }

        if(o instanceof User user){
            int value = this.age - user.age;
            if(value != 0){
                return value;
            }
            //User类中重写compareTo方法时，同时比较了name和age，相当于同时对name和age进行哈希。
            return -this.name.compareTo(user.name);//先按年龄升序，后按姓名升序
        }
        throw new RuntimeException("输入类型不匹配");
    }
}
