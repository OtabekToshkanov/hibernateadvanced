package smartup.academy.dto;

import org.springframework.stereotype.Component;
import smartup.academy.entity.Course;

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

    public CourseDTO toDTO(Course course){
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setInstructorId(course.instructor.getId());
        return courseDTO;
    }

    public List<CourseDTO> toDTOs(List<Course> courses){
        return courses.stream()
                .map(this::toDTO)
                .toList();
    }

    public Course toEntityMerg(Course course, CourseDTO courseDTO) {
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        return course;
    }
}
