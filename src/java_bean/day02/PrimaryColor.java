package java_bean.day02;

/**
 * @ClassName: PrimaryColor.java
 * @Author: anpeng
 * @Date: 2023/11/4 11:06
 */
public enum PrimaryColor {
    RED("红色"),//系统会自动添加public static final修饰
    GREEN("绿色"),
    BLUE("蓝色");

    private final String description;

    PrimaryColor(String description){
        //有参构造函数的private修饰符可以省略
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() +":"+ description;
    }
}
