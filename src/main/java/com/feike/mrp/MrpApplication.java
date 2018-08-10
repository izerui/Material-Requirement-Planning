package com.feike.mrp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MrpApplication {


    @Bean
    public ApplicationPidFileWriter applicationPidFileWriter() {
        return new ApplicationPidFileWriter();
    }

    public static void main(String[] args) {
        SpringApplication.run(MrpApplication.class, args);
    }

}
