package com.feike.mrp.support;

import com.feike.mrp.support.web.GlobResponseBodyAdviceAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by serv on 2016/10/18.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    @ConditionalOnMissingBean
    public GlobResponseBodyAdviceAdapter globRequestBodyAdviceAdapter() {
        return new GlobResponseBodyAdviceAdapter();
    }

}
