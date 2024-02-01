package com.sergioramos.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HikariCpConfig implements ServletContextListener {

    public static final String DATASOURCE_ATTR = "dataSource";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/forum");
        config.setUsername("root");
        config.setPassword("admin123");
        HikariDataSource dataSource = new HikariDataSource(config);
        DataSourceUtil.storeDataSource(dataSource, servletContextEvent.getServletContext());
    }
}
