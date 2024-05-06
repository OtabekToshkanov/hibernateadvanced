package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.InstructorDetail;
import uz.smartup.academy.hibernateadvanced.entity.Role;
import uz.smartup.academy.hibernateadvanced.entity.User;

import java.util.ArrayList;
import java.util.List;
@Component
public class InstructorDTOUtil {
    public Instructor toEntity(InstructorDTO instructorDTO){

        Instructor instructor=new Instructor();
        instructor.setId(instructorDTO.getId());
        User user=new User();
        user.setFirstName(instructorDTO.getFirstName());
        user.setLastName(instructorDTO.getLastName());
        user.setEmail(instructorDTO.getEmail());
        user.setEnabled("Y");
        user.setUserName(instructorDTO.getUserName());
        user.setPassword((instructorDTO.getPassword()));

        List<Role> roles = new ArrayList<>();
        for(String a:instructorDTO.role) {
            Role role = new Role();
            role.setRole(a);
            role.setUsername(instructorDTO.getUserName());
            roles.add(role);
        }
        user.setRoles(roles);
        instructor.setUser(user);
        InstructorDetail instructorDetail=new InstructorDetail();
        instructorDetail.setHobby(instructorDTO.getHobby());
        instructorDetail.setYoutubeChannel(instructorDTO.getYoutubeChannel());
        instructor.setInstructorDetail(instructorDetail);
        return instructor;
    }

    public Instructor toEntity(Instructor instructor,InstructorDTO instructorDTO){
        instructor.setId(instructorDTO.getId());

        instructor.getUser().setFirstName(instructorDTO.getFirstName());
        instructor.getUser().setLastName(instructorDTO.getLastName());
        instructor.getUser().setEmail(instructorDTO.getEmail());
        instructor.getUser().setEnabled("Y");
        instructor.getUser().setUserName(instructorDTO.getUserName());
        instructor.getUser().setPassword(("{noop}"+instructorDTO.getPassword()));

        instructor.getInstructorDetail().setHobby(instructorDTO.getHobby());
        instructor.getInstructorDetail().setYoutubeChannel(instructorDTO.getYoutubeChannel());
        return instructor;
    }

    public InstructorDTO toDTOUser(Instructor instructor){
        InstructorDTO instructorDTO=new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getUser().getFirstName());
        instructorDTO.setLastName(instructor.getUser().getLastName());
        instructorDTO.setEmail(instructor.getUser().getEmail());
        instructorDTO.setUserName(instructor.getUser().getUserName());
        instructorDTO.setPassword(instructor.getUser().getPassword());

        return instructorDTO;
    }
    public InstructorDTO toDTO(Instructor instructor){
        InstructorDTO instructorDTO=new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getUser().getFirstName());
        instructorDTO.setLastName(instructor.getUser().getLastName());
        instructorDTO.setEmail(instructor.getUser().getEmail());
        instructorDTO.setUserName(instructor.getUser().getUserName());
        instructorDTO.setPassword(instructor.getUser().getPassword().substring(6));
        instructorDTO.setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());
        instructorDTO.setHobby(instructor.getInstructorDetail().getHobby());
        return instructorDTO;
    }
    public List<InstructorDTO> toDTOS(List<Instructor> instructors){
        return instructors.stream()
                .map(this::toDTO)
                .toList();
    }

}
