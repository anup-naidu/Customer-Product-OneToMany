package com.javatechie.jpa.config;

import com.sun.xml.bind.v2.runtime.unmarshaller.Intercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessagesConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CustomerRequestInterceptor customerRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(customerRequestInterceptor);
    }

    @Bean
    public ResourceBundleMessageSource messageSource () {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}
