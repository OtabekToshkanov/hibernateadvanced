package smartup.academy.dto;

import org.springframework.stereotype.Component;
import smartup.academy.entity.Student;

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

    public Student toEntity(StudentDTO studentDTO){
        Student student=new Student();
        student.setId(student.getId());
        student.user.setLastName(studentDTO.getLastName());
        student.user.setFirstName(studentDTO.getFirstName());
        student.user.setEmail(studentDTO.getEmail());
        student.user.setUserName(studentDTO.getUserName());
        student.user.setPassword("{noop}"+studentDTO.getPassword());
        return student;
    }

    public Student toEntityMerge(Student student,StudentDTO studentDTO){
        student.user.setLastName(studentDTO.getLastName());
        student.user.setFirstName(studentDTO.getFirstName());
        student.user.setEmail(studentDTO.getEmail());
        student.user.setUserName(studentDTO.getUserName());
        student.user.setPassword("{noop}"+studentDTO.getPassword());
        return student;
    }

    public List<StudentDTO> toDTOs(List<Student> students){
        return students.stream()
                .map(this::toDTO)
                .toList();
    }
}
