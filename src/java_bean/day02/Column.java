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
    String columnName();
    String columnType();
}
