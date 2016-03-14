/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech.config;

import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.hibernate.RequestStatisticsInterceptor;

@Configuration
@Slf4j
@ComponentScan(basePackages = { "pl.java.scalatech.converters", "pl.java.scalatech.web.interceptor" , "pl.java.scalatech.hibernate"})
//@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;
    @Autowired
    private RequestStatisticsInterceptor requestStatisticsInterceptor;

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping hm = super.requestMappingHandlerMapping();
        hm.setRemoveSemicolonContent(false);
        return hm;
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        registration.addInitParameter("webAllowOthers", "true");
        return registration;
    }

     @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/400").setViewName("/error/400");
        registry.addViewController("/404").setViewName("/error/404");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  // mapujemy statyczne zasoby
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(3000);
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/").setCachePeriod(0);
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/resources/images/").setCachePeriod(3000);
        //registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico").setCachePeriod(3000);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/").setCachePeriod(3000);

    }

    
    @Bean
    Resource picture() {
        return new org.springframework.core.io.ClassPathResource("new_mg.png");
    }
    
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(requestStatisticsInterceptor);
    }

    @Bean
    // @Profile("converter")
    public DomainClassConverter<?> domainClassConverter() {
        return new DomainClassConverter<>(mvcConversionService());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        // xceptionResolvers.add(new MyExceptionHandlerResolver());

    }

    @Bean
    public FilterRegistrationBean filterRegistrationBeanEncoding() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

/*    FilterRegistration.Dynamic mdcInsertingServletFilter =
            servletContext.addFilter("mdcInsertingServletFilter", MDCInsertingServletFilter.class);
    mdcInsertingServletFilter.addMappingForUrlPatterns(null, false, "/*");
*/


    @Bean
    public FilterRegistrationBean filterRegistrationBeanHidden() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HiddenHttpMethodFilter());
        return registrationBean;
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
        crlf.setIncludeClientInfo(true);
        crlf.setIncludeQueryString(true);
        crlf.setIncludePayload(true);
        return crlf;
    }



}
