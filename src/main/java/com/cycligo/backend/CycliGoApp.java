package com.cycligo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Created by Mindaugas Urbontaitis on 13/11/2016.
 * CycliGO - Built by cyclists for cyclists.
 */
@SpringBootApplication
public class CycliGoApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CycliGoApp.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(
                Arrays.asList(
                    "http://localhost:3000",
                    "http://localhost:8080",
                    "http://192.168.1.36",
                    "http://test.murbo.net"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
//        return new FilterRegistrationBean(new Filter() {
//            public void doFilter(ServletRequest req, ServletResponse res,
//                                 FilterChain chain) throws IOException, ServletException {
//                HttpServletRequest request = (HttpServletRequest) req;
//                HttpServletResponse response = (HttpServletResponse) res;
//                String method = request.getMethod();
//                // this origin value could just as easily have come from a database
//                response.setHeader("Access-Control-Allow-Origin", origin);
//                response.setHeader("Access-Control-Allow-Methods",
//                        "POST,GET,OPTIONS,DELETE");
//                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader(
//                        "Access-Control-Allow-Headers",
//                        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//                if ("OPTIONS".equals(method)) {
//                    response.setStatus(HttpStatus.OK.value());
//                }
//                else {
//                    chain.doFilter(req, res);
//                }
//            }
//
//            public void init(FilterConfig filterConfig) {
//            }
//
//            public void destroy() {
//            }
//        });
    }
}
