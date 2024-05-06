package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.InstructorDetail;

import java.util.List;

@Component
public class InstructorDTOUtil {
    public Instructor toEntity(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby(instructorDTO.getHobby());
        instructorDetail.setYoutubeChannel(instructorDTO.getYoutubeChannel());

        instructor.setInstructorDetail(instructorDetail);

        return instructor;
    }

    public InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setName(instructor.getName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setHobby(instructor.getInstructorDetail().getHobby());
        instructorDTO.setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());

        return instructorDTO;
    }

    public List<InstructorDTO> toEntities(List<Instructor> instructors) {
        return instructors.stream().map(this::toDTO).toList();
    }
}
