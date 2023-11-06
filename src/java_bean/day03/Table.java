package java_bean.day03;

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
    String value();
}
