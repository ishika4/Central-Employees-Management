package com.example.management.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmployeeUpdateRequestDTO {

    @NotBlank(message = "Name should not be null")
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;

    @Min(value=18,message = "Minimum age required is 18")
    @Digits(fraction = 0, integer = 3)
    private Long age = 0L;

    @NotBlank
    private String location;

    @Email(message = "Please enter a valid email address")
    private String email;

    @Min(value=18,message = "Positive salary amount is required")
    private Long salary;

}
