package com.app.notifications.service;

import com.app.notifications.model.UserDetails;

public interface UserService {

    UserDetails getUserByFirstName(String firstName);

    UserDetails registerUser(UserDetails userDetails);
}
