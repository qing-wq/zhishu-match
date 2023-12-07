package ink.whi.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@EnableRabbit
@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = {"ink.whi.project.modules.user.repo",
        "ink.whi.project.modules.announcement.repo"})
@EnableAspectJAutoProxy
public class ApplicationRun {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationRun.class).allowCircularReferences(true).run(args);
    }
}