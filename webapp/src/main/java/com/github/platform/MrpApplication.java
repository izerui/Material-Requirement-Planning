package com.github.platform;


import com.github.platform.jpa.base.PlatformBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = PlatformBaseRepository.class)
public class MrpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrpApplication.class, args);
    }

}
