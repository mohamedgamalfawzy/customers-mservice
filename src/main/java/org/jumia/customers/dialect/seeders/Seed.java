package org.jumia.customers.dialect.seeders;

import org.springframework.data.repository.CrudRepository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Seed {
    Class<? extends Seeder> seederClass() default Seeder.class;
    Class<? extends CrudRepository> classRepository()  default CrudRepository.class;
}
