package com.app.notifications.service;

import com.app.notifications.model.UserDetails;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

public interface UserService {

    void sendSimpleMessage(String to, String subject, String text);

    @Scheduled(fixedDelay = 5000)
    void sendMailWithScheduler();

    void sendEmailWithAttachments(String toMail) throws MessagingException;

    UserDetails getUserByFirstName(String firstName);

    UserDetails registerUser(UserDetails userDetails);
}
