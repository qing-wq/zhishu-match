package ink.whi.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@EnableRabbit
@ServletComponentScan
@SpringBootApplication
@MapperScan
public class ApplicationRun {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationRun.class).allowCircularReferences(true).run(args);
    }
}