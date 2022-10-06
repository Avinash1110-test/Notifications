package com.app.notifications.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/app/userDetails/")
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

    @PostMapping(value = "registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequestDTO requestDTO) {

        UserDetails userDetails = userDetailsMapper.createRequestToEntity(requestDTO);
        userDetails = userService.registerUser(userDetails);
        UserResponseDTO userResponseDTO = userDetailsMapper.entityToResponse(userDetails);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, userResponseDTO, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
