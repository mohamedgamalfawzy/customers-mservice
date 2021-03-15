package org.jumia.customers.dialect.seeders;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSeeder {
    SeederLevel level() default SeederLevel.AFTER;
    Seed[] seeders() default {};
}
