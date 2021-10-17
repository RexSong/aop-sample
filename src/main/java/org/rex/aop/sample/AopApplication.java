package org.rex.aop.sample;

import org.rex.aop.sample.application.services.Performer;
import org.rex.aop.sample.application.services.PianoShowPerformer;
import org.rex.aop.sample.config.AopApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(AopApplication.class, args);
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopApplicationConfig.class);
      Performer performer = context.getBean("pianoShowPerformer", PianoShowPerformer.class);
      performer.perform();
    }
}
