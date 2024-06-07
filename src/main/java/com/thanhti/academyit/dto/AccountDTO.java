package com.thanhti.academyit.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class AccountDTO  implements Serializable {

    private Long userId;

    @NotEmpty(message = "Username cannot be empty")
    @Length(min = 20, message = "Username must be at least 10 characters long")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Password cannot be empty")
    private String prePassword;

    private Short  role;

}
