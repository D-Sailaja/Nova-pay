package com.uday.novapay.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RegisterRequest {

    @NotBlank(message = "Name is Required")
    @Size(min=3,max=50,message = "Name should be between 3 to 50 characters")
    private String fullName;

    @NotBlank(message = "Email is Required")
    @Email(message = "Enter a valid Email")
    private String email;

    @NotBlank(message = "password is Required ")
    @Size(min = 8,message = "Password should be more than 8 characters")
    private String password;

    @NotBlank(message = "phoneNumber is Required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phoneNumber Should be Exactly 10 digits")
    private String phoneNumber;
}
