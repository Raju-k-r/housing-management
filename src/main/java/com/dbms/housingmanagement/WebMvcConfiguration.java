package com.dbms.housingmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    // == Constants ==
    public static final String PREFIX = "/WEB-INF/views/";
    public static final String SUFFIX = ".jsp";
    public static final String RESOURCE_PATH = "WEB-INF/resources";
    public static final String RESOURCE_PATH_PATTERN = "/**";

    // == Beans ==

    // == View Resolver Bean ==
    @Bean
    public InternalResourceViewResolver viewResolver(){
        // == Creating the view Resolver ==
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        // == Setting prefix value ==
        viewResolver.setPrefix(PREFIX);

        // == Setting suffix value ==
        viewResolver.setSuffix(SUFFIX);

        // == Returning the View Resolver ==
        return viewResolver;
    }

    // == Public Methods ==

    // == Adding the resource handler ==
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // == Adding the resource handler path pattern ==
        registry.addResourceHandler(RESOURCE_PATH_PATTERN)
                // == Adding the resource location path ==
                .addResourceLocations(RESOURCE_PATH);
    }

}
