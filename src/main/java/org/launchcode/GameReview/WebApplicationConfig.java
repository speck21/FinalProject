package org.launchcode.GameReview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    //create spring managed object to allow the app to access auth filter
    @Bean
    public AuthenticationFilter authenticationFilter(){return new AuthenticationFilter();}

    //Register the filter with the Spring container

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((authenticationFilter()));
    }
}
