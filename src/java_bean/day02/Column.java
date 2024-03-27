package java_bean.day02;

import java.lang.annotation.*;

/**
 * @ClassName: Column.java
 * @Author: anpeng
 * @Date: 2023/11/6 10:03
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName();//注解的成员以无参数有返回值的抽象方法的形式来声明
    String columnType();
}
