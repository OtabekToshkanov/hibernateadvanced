package uz.smartup.group2.AcademyProject.dto;

import org.springframework.stereotype.Component;
import uz.smartup.group2.AcademyProject.entity.Student;

@Component
public class StudentDTOUtil {

    public Student toEntity(StudentDTO studentDTO){
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setLocalDateTime(studentDTO.getLocalDateTime());
        student.setPaymentId(studentDTO.getPaymentId());
        student.setRole(studentDTO.getRole());
        return student;
    }

    public StudentDTO toDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setLocalDateTime(student.getLocalDateTime());
        studentDTO.setPaymentId(student.getPaymentId());
        studentDTO.setRole(student.getRole());
        return studentDTO;
    }
}
