package uz.smartup.group2.AcademyProject.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.repository.InstructorRepository;
import uz.smartup.group2.AcademyProject.service.InstructorService;

import java.util.List;

@Component
public class CourseDTOUtil {

    public Course toEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setDecription(courseDTO.getDecription());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseFee(courseDTO.getCourseFee());
        course.setCourseFormat(courseDTO.getCourseFormat());
        course.setCategory(courseDTO.getCategory());
        course.setDuration(courseDTO.getDuration());
        return course;
    }
    public CourseDTO toDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setDecription(course.getDecription());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseFee(course.getCourseFee());
        courseDTO.setCourseFormat(course.getCourseFormat());
        courseDTO.setCategory(course.getCategory());
        courseDTO.setDuration(course.getDuration());
        courseDTO.setInstructorId(course.getInstructor().getId());
        courseDTO.setInstructorName(course.getInstructor().getFirstName()+course.getInstructor().getLastName());
        return courseDTO;
    }
    public List<CourseDTO> toDTOList(List<Course> courses) {
        return courses.stream().map(this::toDTO).toList();
    }
}
