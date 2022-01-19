package com.alkemy.icons.general.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private HttpStatus status;

    private List<String> errors;

}
