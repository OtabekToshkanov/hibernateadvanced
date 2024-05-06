package uz.smartup.group2.AcademyProject.service;

import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.dto.InstructorDTO;
import uz.smartup.group2.AcademyProject.dto.ReviewDTO;
import uz.smartup.group2.AcademyProject.dto.ReviewDTOUtil;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Review;

import java.util.List;

public interface InstructorService {
    public Instructor findByEmail(String email);
    InstructorDTO findById(int id);
    public Instructor save(InstructorDTO instructorDTO);
    List<CourseDTO> getCourses(int id);
    void addCourse(int id, CourseDTO courseDTO);
    void update(InstructorDTO instructorDTO);
    void deleteById(int id);
    List<ReviewDTO> getAllCourseReviews(int courseId);
    ReviewDTO getById(int id);
}
