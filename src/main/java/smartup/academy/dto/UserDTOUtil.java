package smartup.academy.dto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import smartup.academy.entity.Role;
import smartup.academy.entity.Student;
import smartup.academy.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDTOUtil {
public PasswordEncoder passwordEncoder;

    public UserDTOUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserDTO userDTO){
        User user=new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled("Y");
        List<Role> roles = new ArrayList<>();
        for(String a:userDTO.roles) {
            Role role = new Role();
            role.setRole(a);
            role.setUsername(userDTO.getUserName());
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }

    public UserDTO toDTO(User userDTO){
        UserDTO user=new UserDTO();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
//        user.setPassword((userDTO.getPassword().substring(6)));

        return user;
    }

    public List<UserDTO> toDTOS(List<User> users){
        return users.stream()
                .map(this::toDTO)
                .toList();
    }

    public User userMergeEntity(User user, UserDTO userDTO){
//        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
//        user.setPassword("{noop}"+userDTO.getPassword());
        return user;
    }
}
