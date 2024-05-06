package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;

@Component
public class StudentDTOUtil {
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public List<StudentDTO> toDTOs(List<Student> students) {
        return students.stream().map(this::toDTO).toList();
    }
}
