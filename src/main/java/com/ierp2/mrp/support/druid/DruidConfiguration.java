package com.ierp2.mrp.support.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by serv on 16/6/23.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnClass(DataSource.class)
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidConfiguration {


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Primary
    @Bean(name = "druidDataSource", destroyMethod = "close", initMethod = "init")
    public DruidDataSource dataSource(DruidProperties properties) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource(false);
        if (StringUtils.isNotEmpty(properties.getId())) {
            druidDataSource.setName(properties.getId());
        }
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setFilters(properties.getFilters());
//        druidDataSource.getProxyFilters().add(new TenantFilter());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        return druidDataSource;
    }


    @Bean(name = "druidWebStatFilter")
    public FilterRegistrationBean druidWebStatFilter(DruidProperties properties) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<String, String>(1);
        initParameters.put("exclusions", properties.getExclusions());
        initParameters.put("sessionStatMaxCount", properties.getSessionStatMaxCount().toString());
        initParameters.put("sessionStatEnable", String.valueOf(properties.isSessionStatEnable()));
        if (properties.getPrincipalSessionName() != null) {
            initParameters.put("principalSessionName", properties.getPrincipalSessionName());
        }
        initParameters.put("profileEnable", String.valueOf(properties.isProfileEnable()));
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


    @Bean
    public ServletRegistrationBean druid(DruidProperties properties) throws SQLException {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<String, String>();
        map.put("resetEnable", String.valueOf(properties.isResetEnable()));
        if (properties.getLoginUsername() != null) {
            map.put("loginUsername", properties.getLoginUsername());
        }
        if (properties.getLoginPassword() != null) {
            map.put("loginPassword", properties.getLoginPassword());
        }
        if (properties.getJmxUrl() != null) {
            map.put("jmxUrl", properties.getJmxUrl());
        }
        if (properties.getJmxUsername() != null) {
            map.put("jmxUsername", properties.getJmxUsername());
        }
        if (properties.getJmxPassword() != null) {
            map.put("jmxPassword", properties.getJmxPassword());
        }
        registrationBean.setInitParameters(map);
        return registrationBean;
    }

}
