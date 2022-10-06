package com.app.notifications.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDTO {

    private String firstName;

    private String lastName;

    private String dob;

    private String doj;

    private String emailAddress;

    private String contactNo;
}
