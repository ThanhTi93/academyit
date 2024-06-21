package com.thanhti.academyit.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class AccountDTO  implements Serializable {

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Password cannot be empty")
    private String prePassword;

    @Column(name = "confirmation_token")
    private String confirmationToken; // Add confirmationToken field
}
