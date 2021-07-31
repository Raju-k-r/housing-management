package com.dbms.housingmanagement;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebServletInitializer implements WebApplicationInitializer {

    // == Constants ==
    public static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    // == Public Method ==
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // == Creating the Context ==
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // == Registering the Configuration class ==
        context.register(WebMvcConfiguration.class);

        // == Creating a dispatcher servlet ==
        DispatcherServlet servlet = new DispatcherServlet(context);

        // == Registering the bean ==
        ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME,servlet);

        // == Configuring the servlet ==
        registration.setLoadOnStartup(1);

        registration.addMapping("/");
    }
}
