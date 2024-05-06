package uz.smartup.group2.AcademyProject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.smartup.group2.AcademyProject.entity.Course;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InstructorDTO {
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
    private String password;
    private String role;
    private List<Course> courses;
}
