package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Course;

import java.util.List;

@Component
public class CourseDTOUtil {
    public Course toEntity(CourseDTO courseDTO){
        Course course=new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());


        return course;
    }

    public CourseDTO toDTO(Course courseDTO){
        CourseDTO course=new CourseDTO();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setInstructorId(courseDTO.getInstructor().getId());

        return course;
    }

    public List<CourseDTO> toDTOs(List<Course> courses){
        return courses.stream()
                .map(this::toDTO)
                .toList();
    }
}
