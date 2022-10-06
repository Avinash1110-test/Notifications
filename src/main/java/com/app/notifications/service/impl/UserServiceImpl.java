package com.app.notifications.service.impl;

import com.app.notifications.model.UserDetails;
import com.app.notifications.repository.UserRepository;
import com.app.notifications.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }
}
