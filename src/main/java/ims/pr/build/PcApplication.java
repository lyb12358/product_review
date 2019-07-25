package ims.pr.build;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author lyb
 * @create 2019-04-03 16:26
 */
@SpringBootApplication
@ComponentScan(basePackages = "ims")
@EnableJpaRepositories(basePackages = "ims")
@EntityScan(basePackages = "ims")
public class PcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcApplication.class, args);
    }
}
