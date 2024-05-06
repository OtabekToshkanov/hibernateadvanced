package smartup.academy.dto;

import org.springframework.stereotype.Component;
import smartup.academy.entity.Instructor;
import smartup.academy.entity.InstructorDetail;
import smartup.academy.entity.Role;
import smartup.academy.entity.User;

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

        instructor.user.setFirstName(instructorDTO.getFirstName());
        instructor.user.setLastName(instructorDTO.getLastName());
        instructor.user.setEmail(instructorDTO.getEmail());
        instructor.user.setEnabled("Y");
        instructor.user.setUserName(instructorDTO.getUserName());
//        instructor.user.setPassword(("{noop}"+instructorDTO.getPassword()));

        instructor.instructorDetail.setHobby(instructorDTO.getHobby());
        instructor.instructorDetail.setYoutubeChannel(instructorDTO.getYoutubeChannel());
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
