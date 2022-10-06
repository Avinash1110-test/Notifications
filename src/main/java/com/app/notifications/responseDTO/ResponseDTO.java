package com.app.notifications.responseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class ResponseDTO {

    private Integer httpStatus;

    private Boolean success;

    private Date timestamp;

    private Object result;

    private ResponseError error;

    public ResponseDTO(Integer httpStatus, Boolean success, Object result, ResponseError error) {

        this.httpStatus = httpStatus;
        this.success = success;
        this.error = error;
        this.result = result;
    }

    public Date getTimestamp() {

        return Calendar.getInstance().getTime();
    }
}