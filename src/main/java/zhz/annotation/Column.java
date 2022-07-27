package zhz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 龙
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Column {

    String columnName() default "";

    String label() default "";

}
