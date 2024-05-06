package uz.smartup.group2.AcademyProject.dto;

import org.springframework.stereotype.Component;
import uz.smartup.group2.AcademyProject.entity.Instructor;

@Component
public class InstructorDTOUtil {

    public Instructor toEntity(InstructorDTO instructorDTO){
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPassword(instructorDTO.getPassword());
        instructor.setCourses(instructorDTO.getCourses());
        instructor.setRole(instructorDTO.getRole());
        return instructor;
    }
    public InstructorDTO toDTO( Instructor instructor){
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getFirstName());
        instructorDTO.setLastName(instructor.getLastName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setPassword(instructor.getPassword());
        instructorDTO.setCourses(instructorDTO.getCourses());
        instructorDTO.setRole(instructorDTO.getRole());
        return instructorDTO;
    }
}
