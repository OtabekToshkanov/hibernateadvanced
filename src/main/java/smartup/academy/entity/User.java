package smartup.academy.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/*CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT primary key,
	`username` varchar(80) not null,
    `password` varchar(80) not null,
    `enabled` VARCHAR(1) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(505) ,
    `email` varchar(100) ,
    UNIQUE(`username`)
    )EN
    GINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
*/
@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "email")
    public String email;

    @Column(name = "username")
    public String userName;

    @Column(name = "password")
    public String password;

    @Column(name = "enabled")
    public String enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "username", referencedColumnName = "username",updatable = false)
    private List<Role> roles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    public Student student;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    public Instructor instructor;


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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", roles=" + roles +
                '}';
    }
}

