package uz.smartup.group2.AcademyProject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.smartup.group2.AcademyProject.entity.Instructor;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO {
    private int id;
    @NotBlank(message = "Course Name is not provided")
    private String courseName;
    //@Size(min = 100, message = "minimum 100 words")
    private String decription;
    @NotBlank(message = "Duration is not provided")
    @Min(1)
    @Max(12)
    private int duration;
    @Min(0)
    private int courseFee;
    @NotBlank(message = "Course Format is not provided")
    private String courseFormat;
    @NotBlank(message = "Category is not provided")
    private String category;
    private int instructorId;
    private String instructorName;
}
