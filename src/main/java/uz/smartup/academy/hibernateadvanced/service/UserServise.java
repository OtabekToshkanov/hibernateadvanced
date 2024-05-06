package uz.smartup.academy.hibernateadvanced.service;

import uz.smartup.academy.hibernateadvanced.dto.UserDTO;

import java.util.List;

public interface UserServise {

    public void userPersist(UserDTO userDTO);

    public void UserDeleteById(int id);

    public UserDTO userFindById(int id);

    public List<UserDTO> userAll();

    public void userMerge(UserDTO userDTO);

    public List<UserDTO> UserByFirstName(String firstname);
}
