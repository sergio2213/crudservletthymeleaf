package com.sergioramos.config;

import jakarta.servlet.ServletContext;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;

public class TemplateEngineUtil {

    public static void storeTemplateEngine(ITemplateEngine templateEngine, ServletContext servletContext) {
        servletContext.setAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR, templateEngine);
    }

    public static ITemplateEngine getTemplateEngine(ServletContext servletContext) {
        return (TemplateEngine) servletContext.getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);
    }
}
