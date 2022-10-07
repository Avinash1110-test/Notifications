package com.app.notifications.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Configurations {

    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.port}")
    private int smtpPort;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpStartTls;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setPort(smtpPort);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStartTls);
        props.put("mail.debug", "true");

        return mailSender;
    }

}