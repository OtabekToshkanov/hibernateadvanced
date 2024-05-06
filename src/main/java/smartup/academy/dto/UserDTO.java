package smartup.academy.dto;

import smartup.academy.entity.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*
CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT primary key,
	`username` varchar(80) not null,
    `password` varchar(80) not null,
    `enabled` VARCHAR(1) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(505) ,
    `email` varchar(100) ,
*/
public class UserDTO {

    public int id;

    public String userName;

    public String password;

    public String firstName;

    public String lastName;

    public String email;

    public String[] roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
