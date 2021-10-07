package com.pedroha.emailms.adapters.configurations;

import com.pedroha.emailms.EmailMicroserviceApplication;
import com.pedroha.emailms.appCore.ports.EmailRepositoryPort;
import com.pedroha.emailms.appCore.ports.SendEmailServicePort;
import com.pedroha.emailms.appCore.services.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;


@Configuration
@ComponentScan(basePackageClasses = EmailMicroserviceApplication.class)
public class BeanConfig {

    @Bean
    EmailServiceImpl emailServiceImpl(EmailRepositoryPort repository, SendEmailServicePort sendEmailServicePort) {
        return new EmailServiceImpl(repository, sendEmailServicePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}