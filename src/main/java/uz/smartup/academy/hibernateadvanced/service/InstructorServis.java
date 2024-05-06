package uz.smartup.academy.hibernateadvanced.service;

import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;

import java.util.List;

public interface InstructorServis {

    void instructorPersist(InstructorDTO instructorDTO);

    InstructorDTO instructorFindById(int id);

    List<InstructorDTO> findInstructorByFirstName(String firstName);

    List<InstructorDTO> instructorAll();

    void instructorMerge(InstructorDTO instructorDTO);

    void instructorDeleteById(int id);

    void instructorAddCourse(CourseDTO courseDTO);

    List<CourseDTO> instructorAllCourse(int id);

    List<CourseDTO>findInstructorCourseBuTitle(int instructorId, String title);

    void instructorAddCourseSave(CourseDTO courseDTO);

}
