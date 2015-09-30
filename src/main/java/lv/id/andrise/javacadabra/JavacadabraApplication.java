package lv.id.andrise.javacadabra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class JavacadabraApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavacadabraApplication.class, args);
    }
}
//TODO 1: Add Junit tests
//TODO 2: Add Integration tests
//TODO 3: Split MainController into logical parts