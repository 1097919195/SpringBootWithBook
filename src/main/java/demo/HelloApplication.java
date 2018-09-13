package demo;

import java.nio.charset.Charset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@Configuration
@ComponentScan(basePackages="demo")
public class HelloApplication {

    public static void main(String[] args) {
       // SpringApplication.run(HelloApplication.class, args);
        SpringApplication application = new SpringApplication(HelloApplication.class);
        //关闭banner横幅
        //application.setBannerMode(Mode.OFF);
        application.run(args);
    }

}
