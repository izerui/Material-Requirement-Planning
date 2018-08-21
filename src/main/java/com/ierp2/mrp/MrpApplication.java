package com.ierp2.mrp;


import com.ierp2.mrp.configuration.jpa.factory.PlatformJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = PlatformJpaRepositoryFactoryBean.class)
public class MrpApplication {

    @Bean
    public ApplicationPidFileWriter applicationPidFileWriter() {
        return new ApplicationPidFileWriter();
    }

    public static void main(String[] args) {
        SpringApplication.run(MrpApplication.class, args);
    }

}
