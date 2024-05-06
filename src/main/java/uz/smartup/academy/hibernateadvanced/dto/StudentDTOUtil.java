package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;

@Component
public class StudentDTOUtil {
    public StudentDTO toDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setLastName(student.getUser().getLastName());
        studentDTO.setFirstName(student.getUser().getFirstName());
        studentDTO.setEmail(student.getUser().getEmail());
        studentDTO.setUserName(student.getUser().getUserName());
        studentDTO.setPassword(student.getUser().getPassword().substring(6));
        return studentDTO;
    }
//
//    public Student toEntity(StudentDTO dto) {
//        Student student = new Student();
//        User user = new User();
//        user.setUsername(dto.getUsername());
//        user.setPassword(dto.getPassword());
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        user.setEmail(dto.getEmail());
//        student.setUser(user);
//        return student;
//    }
    public List<StudentDTO> toDTOs(List<Student> students) {
        return students.stream().map(this::toDTO).toList();
    }



}
