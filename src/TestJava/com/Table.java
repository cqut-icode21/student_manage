package TestJava.com;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    // 表名
    String tableName() default "";

    // 属性描述
    String label() default "";
}