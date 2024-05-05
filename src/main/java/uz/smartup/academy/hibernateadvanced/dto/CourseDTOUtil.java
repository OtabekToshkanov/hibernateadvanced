package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Course;

import java.util.List;

@Component
public class CourseDTOUtil {
    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());

        return course;
    }

    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO.Builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .instructorId(course.getInstructor().getId())
                .build();

        return courseDTO;
    }

    public List<CourseDTO> toDTOs(List<Course> courses) {
        return courses.stream().map(this::toDTO).toList();
    }
}
