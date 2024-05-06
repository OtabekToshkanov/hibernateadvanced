package uz.smartup.group2.AcademyProject.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private int id;

    private int rating;

    private String comment;

    private int courseId;

    private int studentId;
}
