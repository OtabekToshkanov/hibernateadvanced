package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTOUtil;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTOUtil;
import uz.smartup.academy.hibernateadvanced.dto.UserDTO;
import uz.smartup.academy.hibernateadvanced.dto.UserDTOUtil;
import uz.smartup.academy.hibernateadvanced.entity.Student;
import uz.smartup.academy.hibernateadvanced.entity.User;

import java.util.Arrays;
import java.util.List;

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

    @Transactional
    @Override
    public void userPersist(UserDTO userDTO) {
        User user=userDTOUtil.toEntity(userDTO);
        if (Arrays.asList(userDTO.getRoles()).contains("ROLE_STUDENT")){
            Student student=new Student();
            student.setUser(user);
            appDAO.studentPersist(student);
        }
        else{
            if(!Arrays.asList(userDTO.getRoles()).contains("ROLE_INSTRUCTOR")){
                appDAO.userPersist(user);
            }
        }
    }
    @Transactional
    @Override
    public void UserDeleteById(int id) {
        User user = appDAO.userFindById(id);
        appDAO.UserDeleteById(user);
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

    @Override
    public List<UserDTO> UserByFirstName(String firstname) {
        List<User> users= appDAO.userFindByFirstName(firstname);
        //System.out.println(users);

        return userDTOUtil.toDTOS(users);
    }
}
