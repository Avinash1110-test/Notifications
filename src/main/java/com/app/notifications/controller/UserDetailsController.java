package com.app.notifications.controller;

import com.app.notifications.exception.ServiceException;
import com.app.notifications.mapper.UserDetailsMapper;
import com.app.notifications.model.UserDetails;
import com.app.notifications.requestDTO.RegisterUserRequestDTO;
import com.app.notifications.responseDTO.ResponseDTO;
import com.app.notifications.responseDTO.UserResponseDTO;
import com.app.notifications.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/app/userDetails/")
public class UserDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @GetMapping(value = "health")
    public ResponseEntity<String> healthCheck() {

        logger.info("AppController - Health check is called successfully.");
        return new ResponseEntity<>("Application is ready.", HttpStatus.OK);
    }

    @GetMapping(value = "sendEmail")
    public ResponseEntity<?> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) throws MessagingException {

        userService.sendSimpleMessage(to, subject, text);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, "Mail sent successfully.", null);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(value = "sendMailWithScheduler")
    @Scheduled(fixedDelay = 10000)
    public ResponseEntity<?> sendMailWithScheduler() {

        userService.sendMailWithScheduler();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, "Mail sent successfully.", null);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(value = "sendEmailWithAttachments")
    public ResponseEntity<?> sendEmailWithAttachments(@RequestParam String to) throws MessagingException {

        userService.sendEmailWithAttachments(to);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, "Mail sent successfully.", null);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(value = "getUserByFirstName/{firstName}")
    public ResponseEntity<?> getUserByFirstName(@PathVariable("firstName") String firstName) {

        UserDetails userDetails = userService.getUserByFirstName(firstName);
        if (userDetails == null) {
            throw new ServiceException("First name doesn't exists, please enter valid first name.", HttpStatus.NOT_FOUND);
        }
        UserResponseDTO userResponseDTO = userDetailsMapper.entityToResponse(userDetails);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, userResponseDTO, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping(value = "registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequestDTO requestDTO) {

        UserDetails userDetails = userDetailsMapper.createRequestToEntity(requestDTO);
        userDetails = userService.registerUser(userDetails);
        UserResponseDTO userResponseDTO = userDetailsMapper.entityToResponse(userDetails);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, userResponseDTO, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
