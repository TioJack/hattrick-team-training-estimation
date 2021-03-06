package net.ddns.tiojack.htte;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication()
public class Application {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("spring.config.name:application")
                .run(args);
    }

}
