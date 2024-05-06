package uz.smartup.academy.hibernateadvanced.dto;


import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Role;
import uz.smartup.academy.hibernateadvanced.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOUtil {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword("{noop}" + userDTO.getPassword());
        user.setEnabled("Y");
        List<Role> roles = new ArrayList<>();
        for (String a : userDTO.roles) {
            Role role = new Role();
            role.setRole(a);
            role.setUsername(userDTO.getUserName());
            roles.add(role);
        }
        user.setRoles(roles);

        return user;
    }

    public UserDTO toDTO(User userDTO) {
        UserDTO user = new UserDTO();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword((userDTO.getPassword().substring(6)));

        return user;
    }

    public List<UserDTO> toDTOS(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .toList();
    }


    public User userMergeEntity(User user, UserDTO userDTO) {
 //       user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword("{noop}" + userDTO.getPassword());
        return user;
    }
}
