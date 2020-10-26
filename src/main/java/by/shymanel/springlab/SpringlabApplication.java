package by.shymanel.springlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"by.shymanel.springlab.model"} )
@EnableJpaRepositories(basePackages = {"by.shymanel.springlab.repository"})
public class SpringlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringlabApplication.class, args);
    }

}
