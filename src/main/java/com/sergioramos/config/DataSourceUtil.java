package com.sergioramos.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;

public class DataSourceUtil {
    public static void storeDataSource(HikariDataSource dataSource, ServletContext servletContext) {
        servletContext.setAttribute(HikariCpConfig.DATASOURCE_ATTR, dataSource);
    }

    public static HikariDataSource getDataSource(ServletContext servletContext) {
        return (HikariDataSource) servletContext.getAttribute(HikariCpConfig.DATASOURCE_ATTR);
    }
}
