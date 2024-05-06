package uz.smartup.academy.hibernateadvanced.service;

import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {
    void createInstructor(InstructorDTO instructorDTO);

    List<InstructorDTO> getInstructors();

    InstructorDTO getInstructor(int id);

    void updateInstructor(InstructorDTO instructorDTO);

    void deleteInstructor(int id);

    void addCourse(int id, CourseDTO courseDTO);

    List<CourseDTO> getCourses(int id);
}
