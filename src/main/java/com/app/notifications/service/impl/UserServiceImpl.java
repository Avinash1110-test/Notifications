package com.app.notifications.service.impl;

import com.app.notifications.exception.ServiceException;
import com.app.notifications.model.UserDetails;
import com.app.notifications.repository.UserRepository;
import com.app.notifications.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender emailSender;

    /* send a normal mail
    *  here we are taking To mail id from method parameter, we can take To mail id from database as well by using userId, firstName, etc,.*/
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        try {
            logger.info("sendSimpleMessage is called with inputs - to: {}, subject: {}, text: {}", to, subject, text);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /* send a mail with set of intervals, it'll automatically triggers mail
       here we are hardcoding To mail id, we can take To mail id from database as well by using userId, firstName, etc,. */
    @Override
    @Scheduled(fixedDelay = 10000) //set time, here we set it for 10 seconds
    public void sendMailWithScheduler() {

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            logger.info("sendSimpleMessage is called at : {}", dtf.format(now));
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo("avinashakkera123@gmail.com");
            message.setSubject("Test scheduler");
            message.setText("Hi again..!");
            emailSender.send(message);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /* send email with attachment, here we are using file from local
    here we are taking to mail id from method parameter, we can take To mail id from database as well by using userId, firstName, etc,. */
    @Override
    public void sendEmailWithAttachments(String toMail) {

        try {
            String path = "README.md";
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toMail);
            helper.setText("Hi, Please check attachment.", true);
            FileSystemResource file  = new FileSystemResource(new File(path));
            helper.addAttachment("testFile", file);
            helper.setSubject("Test mail with attachment.");
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new ServiceException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public UserDetails getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }
}
