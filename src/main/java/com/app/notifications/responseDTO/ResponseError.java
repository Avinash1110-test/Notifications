package com.app.notifications.responseDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonRootName(value = "error")
public class ResponseError {

    private String code;

    private List<String> message;
}