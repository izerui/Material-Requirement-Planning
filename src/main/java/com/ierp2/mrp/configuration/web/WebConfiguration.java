package com.ierp2.mrp.configuration.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by serv on 2016/10/18.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }


    @Bean
    @ConditionalOnMissingBean
    public GlobResponseBodyAdviceAdapter globRequestBodyAdviceAdapter() {
        return new GlobResponseBodyAdviceAdapter();
    }

}
