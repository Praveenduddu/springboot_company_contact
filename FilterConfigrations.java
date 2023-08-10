package de.zeroco.companycontact.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.zeroco.companycontact.filter.Filtere;

@Configuration
public class FilterConfigrations{

	@Autowired
	Filtere filtere;
	
	@Bean
    FilterRegistrationBean<Filtere> loggingFilter() {FilterRegistrationBean<Filtere> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filtere);
        registrationBean.addUrlPatterns("/contact/insert", "/contact/update", "/company/insert", "/company/update");
        return registrationBean;
    }
	
	
}
