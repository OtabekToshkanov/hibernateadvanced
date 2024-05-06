package smartup.academy.serves;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import smartup.academy.dto.UserDTO;

import java.util.List;

public interface UserServise {

    public void userPersist(UserDTO userDTO);

    public void UserDeleteById(int id);

    public UserDTO userFindById(int id);

    public List<UserDTO> userAll();

    public void userMerge(UserDTO userDTO);

    public UserDetails getLoggedUser();

    UserDTO userFindByUsername(String username);

}
