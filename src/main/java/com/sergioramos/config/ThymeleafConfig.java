package com.sergioramos.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@WebListener
public class ThymeleafConfig implements ServletContextListener {

    public static final String TEMPLATE_ENGINE_ATTR = "templateEngine";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        IWebApplication webApplication = JakartaServletWebApplication.buildApplication(servletContextEvent.getServletContext());
        ITemplateEngine templateEngine = templateEngine(webApplication);
        TemplateEngineUtil.storeTemplateEngine(templateEngine, servletContextEvent.getServletContext());
    }

    private ITemplateEngine templateEngine(IWebApplication application) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver(application));
        return templateEngine;
    }

    private ITemplateResolver templateResolver(IWebApplication application) {
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
