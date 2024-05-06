package smartup.academy.serves;

import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import smartup.academy.dao.AppDAO;
import smartup.academy.dto.*;
import smartup.academy.entity.Role;
import smartup.academy.entity.Student;
import smartup.academy.entity.User;

import java.util.*;

@Service
public class UserServiseImpl implements UserServise{
    AppDAO appDAO;
    UserDTOUtil userDTOUtil;
    StudentDTOUtil studentDTOUtil;
    InstructorDTOUtil instructorDTOUtil;
    public UserServiseImpl(AppDAO appDAO, UserDTOUtil userDTOUtil,StudentDTOUtil studentDTOUtil,InstructorDTOUtil instructorDTOUtil) {
        this.appDAO = appDAO;
        this.userDTOUtil = userDTOUtil;
        this.studentDTOUtil=studentDTOUtil;
        this.instructorDTOUtil=instructorDTOUtil;
    }

    public UserDetails getLoggedUser(){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            return (UserDetails) principal;
        }
        return null;
    }

    @Override
    public UserDTO userFindByUsername(String username) {
        UserDTO userDTO=userDTOUtil.toDTO(appDAO.userFindByUsername(username));
        return userDTO;
    }


    @Transactional
    @Override
    public void userPersist(UserDTO userDTO) {
        User user=userDTOUtil.toEntity(userDTO);
        if (Arrays.asList(userDTO.getRoles()).contains("ROLE_STUDENT")){
            Student student=new Student();
            student.setUser(user);
            appDAO.studentPersist(student);
        }
        else {
            if(!Arrays.asList(userDTO.getRoles()).contains("ROLE_INSTRUCTOR")){
               appDAO.userPersist(user);
            }
        }

    }
    @Transactional
    @Override
    public void UserDeleteById(int id) {
        User user = appDAO.userFindById(id);
        appDAO.userDeleteById(user);
    }
    @Override
    public UserDTO userFindById(int id) {
        return userDTOUtil.toDTO(appDAO.userFindById(id));
    }

    @Override
    public List<UserDTO> userAll() {
        List<User> users=appDAO.userAll();
        return userDTOUtil.toDTOS(appDAO.userAll());
    }

    @Transactional
    @Override
    public void userMerge(UserDTO userDTO) {
        User user= appDAO.userFindById(userDTO.getId());
        System.out.println(appDAO.userFindByRoles(userDTO.getUserName()));
        appDAO.userMerge(userDTOUtil.userMergeEntity(user,userDTO));
    }
}
