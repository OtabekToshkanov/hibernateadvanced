package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Student;
import uz.smartup.academy.hibernateadvanced.entity.User;

import java.util.List;

@Component
public class StudentDTOUtil {
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO.Builder()
                                    .id(student.getId())
                                    .userName(student.getUser().getUsername())
                                    .firstName(student.getUser().getFirstName())
                                    .lastName(student.getUser().getLastName())
                                    .email(student.getUser().getEmail())
                                    .password(student.getUser().getPassword())
                                    .build();

        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        User user = new User();
        student.setId(studentDTO.getId());
        user.setUsername(studentDTO.getUserName());
        user.setFirstName(studentDTO.getFirstName());
        user.setLastName(studentDTO.getLastName());
        user.setEmail(studentDTO.getEmail());
        user.setPassword(studentDTO.getPassword());
        student.setUser(user);
        return student;
    }

    public List<StudentDTO> toDTOs(List<Student> students) {
        return students.stream().map(this::toDTO).toList();
    }
}
