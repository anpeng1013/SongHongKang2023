package java_bean.day02;

import java.lang.annotation.*;

/**
 * @ClassName: Table.java
 * @Author: anpeng
 * @Date: 2023/11/6 9:52
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    /*
    如果定义的注解含有抽象方法，那么使用时必须指定返回值，除非它有默认值。格式是“方法名 = 返回值”，如果只有一个抽象方法需要赋值，
    且方法名为 value，可以省略“value=”，所以如果注解只有一个抽象方法成员，建议使用方法名 value。
     */
    String value();//注解的成员以无参数有返回值的抽象方法的形式来声明
}
