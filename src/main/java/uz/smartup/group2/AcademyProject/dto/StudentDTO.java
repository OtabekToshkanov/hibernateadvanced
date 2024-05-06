package uz.smartup.group2.AcademyProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private int id;
    @NotBlank(message = "Please provide a FirstName")
    private String firstName;
    @NotBlank(message = "Please provide a lastName")
    private String lastName;
    @NotBlank(message = "Please provide a email")
    @Email
    private String email;
    @NotBlank(message = "Please provide a Password")
    @Size(min = 6, message = "Password must be at least 8 character long")
    //@Pattern(regexp = "^[a-zA-z0-9]{5}", message = "only 5 character")
    private String password;
    private LocalDateTime localDateTime;
    private int paymentId;
    private String role;
}