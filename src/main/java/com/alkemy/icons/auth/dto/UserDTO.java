package com.alkemy.icons.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Email(message = "must be a valid email")
    private String username;

    @Size(min = 8, message = "must have a minimum of 8 characters")
    private String password;
    private Boolean accountNonBlocked = true;
    private Boolean accountIsActive = true;
    private Boolean credentialsNonExpired = true;

}
