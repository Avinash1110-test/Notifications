package com.app.notifications.mapper;

import com.app.notifications.model.UserDetails;
import com.app.notifications.requestDTO.RegisterUserRequestDTO;
import com.app.notifications.responseDTO.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO entityToResponse(UserDetails userDetails) {
        UserResponseDTO employeeMasterResponseDTO = modelMapper.map(userDetails, UserResponseDTO.class);
        employeeMasterResponseDTO.setId(employeeMasterResponseDTO.getId());
        return employeeMasterResponseDTO;
    }

    public UserDetails createRequestToEntity(RegisterUserRequestDTO registerUserRequestDTO) {
        return modelMapper.map(registerUserRequestDTO, UserDetails.class);
    }

    public void updateRequestToEntity(UserResponseDTO userResponseDTO, UserDetails userDetails) {
        modelMapper.map(userResponseDTO, userDetails);
    }

    public Page<UserResponseDTO> entityPageToResponsePage(Pageable pageable, Page<UserDetails> userDetailsPage) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        userDetailsPage.forEach(userDetails -> userResponseDTOS.add(entityToResponse(userDetails)));
        return new PageImpl<>(userResponseDTOS, pageable, userDetailsPage.getTotalElements());
    }
}