package org.jumia.customers.dialect.seeders;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Aspect
@Component("seederInterceptor")
@Slf4j
public class SeederInterceptor implements ApplicationContextAware {
    private ApplicationContext context;

    @Before(value = "@within(org.jumia.customers.dialect.seeders.EnableSeeder) || @annotation(org.jumia.customers.dialect.seeders.EnableSeeder)")
    public void before(JoinPoint joinPoint)  {
      log.info("Seeder Context enabled in class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() );
        EnableSeeder enableSeeder = (EnableSeeder) joinPoint.getSignature().getDeclaringType().getAnnotation(EnableSeeder.class);
        if (enableSeeder.level()== SeederLevel.BEFORE) {seedObjects(joinPoint);}
    }

    @After(value = "@within(org.jumia.customers.dialect.seeders.EnableSeeder) || @annotation(org.jumia.customers.dialect.seeders.EnableSeeder)")
    public void after(JoinPoint joinPoint) throws Throwable {
        EnableSeeder enableSeeder = (EnableSeeder) joinPoint.getSignature().getDeclaringType().getAnnotation(EnableSeeder.class);
        if (enableSeeder.level()== SeederLevel.AFTER) {seedObjects(joinPoint);}
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    private void seedObjects(JoinPoint joinPoint){
        log.info("Starting Context Seeder for data requested ");
        try {
            EnableSeeder enableSeeder = (EnableSeeder) joinPoint.getSignature().getDeclaringType().getAnnotation(EnableSeeder.class);
            Seed[] seeds= enableSeeder.seeders();
            for (Seed seed : seeds){
                Seeder seeder=this.context.getBean(seed.seederClass()) !=null ?this.context.getBean(seed.seederClass()) : seed.seederClass().newInstance();
                CrudRepository crudRepo=this.context.getBean(seed.classRepository());
                List list=new ArrayList();
                seeder.seed(list);
                int count =0;
                for (Object repo : list){
                    crudRepo.save(repo);
                    count++;
                }
                log.info("Seeding Context for  "+seeder.getClass().getSimpleName()+" has been completed. Total items imported/modified : " +count );
            }
        }catch (Exception e){
            log.error("Stopping Seeder context due an error. Error Details " , e);
            return;
        }
        log.info("Seeding Context Completed");
    }
}
