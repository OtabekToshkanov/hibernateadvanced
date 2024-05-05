package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.InstructorDetail;
import uz.smartup.academy.hibernateadvanced.entity.User;

import java.util.List;

@Component
public class InstructorDTOUtil {
    public Instructor toEntity(InstructorDTO instructorDTO) {
        User user = new User();
        user.setUsername(instructorDTO.getUserName());
        user.setFirstName(instructorDTO.getFirstName());
        user.setLastName(instructorDTO.getLastName());
        user.setEmail(instructorDTO.getEmail());
        user.setPassword(instructorDTO.getPassword());

        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setUser(user);

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby(instructorDTO.getHobby());
        instructorDetail.setYoutubeChannel(instructorDTO.getYoutubeChannel());

        instructor.setInstructorDetail(instructorDetail);

        return instructor;
    }

    public InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getUser().getFirstName());
        instructorDTO.setLastName(instructor.getUser().getLastName());
        instructorDTO.setEmail(instructor.getUser().getEmail());
        instructorDTO.setHobby(instructor.getInstructorDetail().getHobby());
        instructorDTO.setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());

        return instructorDTO;
    }

    public List<InstructorDTO> toEntities(List<Instructor> instructors) {
        return instructors.stream().map(this::toDTO).toList();
    }
}
